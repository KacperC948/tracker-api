package org.example.trackerapi.controller;

import lombok.RequiredArgsConstructor;
import org.example.trackerapi.model.Type;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api/animalType")
@RequiredArgsConstructor
public class AnimalTypeController {

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(List.of(Type.COW, Type.SHEEP, Type.BULL));
    }
}
