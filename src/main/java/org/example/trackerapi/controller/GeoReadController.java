package org.example.trackerapi.controller;

import lombok.RequiredArgsConstructor;
import org.example.trackerapi.dto.GeoReadDto;
import org.example.trackerapi.model.Animal;
import org.example.trackerapi.model.AnimalShepherd;
import org.example.trackerapi.model.GeoRead;
import org.example.trackerapi.model.GeoReadSummary;
import org.example.trackerapi.service.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.geom.Path2D;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api/geoRead")
@RequiredArgsConstructor
public class GeoReadController {

    @Autowired
    private GeoReadService geoReadService;

    @Autowired
    private AnimalService animalService;

    @Autowired
    private AnimalShepherdService animalShepherdService;

    @Autowired
    private GeoReadSummaryService geoReadSummaryService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/add")
    public ResponseEntity<String> add(
            @RequestBody GeoReadDto geoReadDto
    ) {
        Animal animal = animalService.getAnimal(geoReadDto.getAnimalId());
        GeoRead geoRead = GeoRead.builder()
                            .animalId(geoReadDto.getAnimalId())
                            .trackerId(geoReadDto.getTrackerId())
                            .createdDate(geoReadDto.getCreatedDate())
                            .isTempExceeded(geoReadDto.getCurrentTemp() > animal.getTempMax())
                            .tempExceededConfirmed(false)
                            .isAnimalInShepherd(checkAnimalInShepherd(animal.getId(), geoReadDto.getLatitude(), geoReadDto.getLongitude()))
                            .animalInShepherdConfirmed(false)
                            .latitude(geoReadDto.getLatitude())
                            .longitude(geoReadDto.getLongitude())
                            .currentTemp(geoReadDto.getCurrentTemp())
                            .build();

        geoReadService.save(geoRead);

        List<GeoRead> geoReads = geoReadService.getAllByAnimalIdAndDate(geoRead.getAnimalId(), geoRead.getCreatedDate().toLocalDate());
        geoReads.add(geoRead);

        GeoReadSummary geoReadSummary = geoReadSummaryService.getByDateAndAnimalId(geoRead.getCreatedDate().toLocalDate(), geoRead.getAnimalId());
        if(geoReadSummary == null) {
            geoReadSummary = GeoReadSummary.builder()
                                .date(geoRead.getCreatedDate().toLocalDate())
                                .animalId(geoRead.getAnimalId())
                                .distance(geoReadService.calculateTotalDistance(geoReads))
                                .tempAvg(geoReadService.calculateAverageTemperature(geoReads))
                                .outOfShepherdCounter(geoReadService.countAnimalsInShepherd(geoReads))
                                .idleTime(geoReadService.calculateIdleTime(geoReads))
                                .build();
        } else {
            geoReadSummary.setDistance(geoReadService.calculateTotalDistance(geoReads));
            geoReadSummary.setTempAvg(geoReadService.calculateAverageTemperature(geoReads));
            geoReadSummary.setOutOfShepherdCounter(geoReadService.countAnimalsInShepherd(geoReads));
            geoReadSummary.setIdleTime(geoReadService.calculateIdleTime(geoReads));
        }
        geoReadSummaryService.save(geoReadSummary);
        return ResponseEntity.ok("GeoRead added successfully");
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<GeoRead>> getAllByDataRange(
            @RequestParam String startDate,
            @RequestParam String endDate
    ) {
        return ResponseEntity.ok(geoReadService.getByDateRange(startDate, endDate));
    }

    @GetMapping("/getAllWarnings")
    public ResponseEntity<List<GeoRead>> getAllWarnings() {
        return ResponseEntity.ok(geoReadService.getAllWarnings());
    }

    private boolean checkAnimalInShepherd(long animalId, double latitude, double longitude) {
        Animal animal = animalService.getAnimal(animalId);
        List<AnimalShepherd> animalShepherds = animalShepherdService.getAllByType(animal.getType());

        return isPointInRegion(animalShepherds, latitude, longitude);
    }

    public static boolean isPointInRegion(List<AnimalShepherd> regionPoints, double latitude, double longitude) {
        if (regionPoints == null || regionPoints.size() < 3) {
            throw new IllegalArgumentException("Region must be defined by at least 3 points");
        }

        Path2D.Double polygon = new Path2D.Double();
        AnimalShepherd firstPoint = regionPoints.getFirst();
        polygon.moveTo(firstPoint.getLongitude(), firstPoint.getLatitude());

        for (int i = 1; i < regionPoints.size(); i++) {
            AnimalShepherd p = regionPoints.get(i);
            polygon.lineTo(p.getLongitude(), p.getLatitude());
        }
        polygon.closePath();

        return polygon.contains(latitude, longitude);
    }


    private GeoReadDto convertToDto(GeoRead geoRead) {
        return modelMapper.map(geoRead, GeoReadDto.class);
    }
}
