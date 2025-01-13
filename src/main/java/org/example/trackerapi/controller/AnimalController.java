package org.example.trackerapi.controller;

import lombok.RequiredArgsConstructor;
import org.example.trackerapi.dto.AnimalDto;
import org.example.trackerapi.model.Animal;
import org.example.trackerapi.service.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api/animal")
@RequiredArgsConstructor
public class AnimalController {

    @Autowired
    private AnimalService animalService;

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
}
