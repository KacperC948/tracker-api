package org.example.trackerapi.controller;

import lombok.RequiredArgsConstructor;
import org.example.trackerapi.dto.AnimalDto;
import org.example.trackerapi.dto.AnimalShepherdDto;
import org.example.trackerapi.dto.AnimalWithTrackerAndShepherdPoints;
import org.example.trackerapi.dto.TrackerDto;
import org.example.trackerapi.model.Animal;
import org.example.trackerapi.model.AnimalShepherd;
import org.example.trackerapi.model.Tracker;
import org.example.trackerapi.service.AnimalService;
import org.example.trackerapi.service.AnimalShepherdService;
import org.example.trackerapi.service.TrackerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api/animal")
@RequiredArgsConstructor
public class AnimalController {

    @Autowired
    private AnimalService animalService;

    @Autowired
    private TrackerService trackerService;

    @Autowired
    private AnimalShepherdService animalShepherdService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/add")
    public ResponseEntity<String> add(
            @RequestBody AnimalDto animalDto
    ) {
        Animal animal = Animal.builder()
                            .name(animalDto.getName())
                            .tempMax(animalDto.getTempMax())
                            .tempMin(animalDto.getTempMin())
                            .type(animalDto.getType())
                            .build();

        animalService.addAnimal(animal);
        return ResponseEntity.ok("Animal added successfully");
    }

    @GetMapping("/getAll")
    public ResponseEntity<Iterable<AnimalWithTrackerAndShepherdPoints>> getAll() {
        List<AnimalWithTrackerAndShepherdPoints> result = new ArrayList<>();
        List<Animal> animals = animalService.getAnimals();
        animals.forEach(animal -> {
                result.add(AnimalWithTrackerAndShepherdPoints.builder()
                        .animal(AnimalDto.builder()
                                .name(animal.getName())
                                .tempMax(animal.getTempMax())
                                .tempMin(animal.getTempMin())
                                .type(animal.getType())
                                .build())
                        .tracker(convertTrackerToDto(trackerService.getTrackerByAnimalId(animal.getId())))
                        .shepherdPoints(convertAnimalShepherdsToDto(animalShepherdService.getAllByType(animal.getType())))
                        .build());
        });
        return ResponseEntity.ok(result);
    }

    private TrackerDto convertTrackerToDto(Tracker tracker) {
        return modelMapper.map(tracker, TrackerDto.class);
    }

    private List<AnimalShepherdDto> convertAnimalShepherdsToDto(List<AnimalShepherd> animalShepherds) {
        List<AnimalShepherdDto> result = new ArrayList<>();
        animalShepherds.forEach(animalShepherd -> {
            result.add(modelMapper.map(animalShepherd, AnimalShepherdDto.class));
        });
        return result;
    }
}
