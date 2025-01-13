package org.example.trackerapi.controller;

import lombok.RequiredArgsConstructor;
import org.example.trackerapi.dto.AnimalShepherdDto;
import org.example.trackerapi.model.AnimalShepherd;
import org.example.trackerapi.service.AnimalShepherdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api/animalShepherd")
@RequiredArgsConstructor
public class AnimalShepherdController {

    @Autowired
    private AnimalShepherdService animalShepherdService;

    @PostMapping("/add")
    public ResponseEntity<String> add(
            @RequestBody List<AnimalShepherdDto> animalShepherdDtos
    ) {
        List<AnimalShepherd> animalShepherdList = new ArrayList<>();
        for (AnimalShepherdDto animalShepherdDto : animalShepherdDtos) {
            AnimalShepherd animalShepherd = AnimalShepherd.builder()
                                .animalType(animalShepherdDto.getAnimalType())
                                .latitude(animalShepherdDto.getLatitude())
                                .longitude(animalShepherdDto.getLongitude())
                                .build();

            animalShepherdList.add(animalShepherd);
        }

        animalShepherdService.saveAll(animalShepherdList);
        return ResponseEntity.ok("AnimalShepherds added successfully");
    }

    @GetMapping("/getAllByType")
    public ResponseEntity<List<AnimalShepherd>> getAllByType(
            @RequestParam String type
    ) {
        return ResponseEntity.ok(animalShepherdService.getAllByType(type));
    }
}
