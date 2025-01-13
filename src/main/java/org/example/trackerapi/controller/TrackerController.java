package org.example.trackerapi.controller;


import lombok.RequiredArgsConstructor;
import org.example.trackerapi.dto.TrackerDto;
import org.example.trackerapi.model.Tracker;
import org.example.trackerapi.service.TrackerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api/tracker")
@RequiredArgsConstructor
public class TrackerController {

    @Autowired
    TrackerService trackerService;

    @PostMapping("/add")
    public ResponseEntity<String> add(
            @RequestBody TrackerDto trackerDto
    ) {
        Tracker tracker = Tracker.builder()
                .animalId(trackerDto.getAnimalId())
                .build();

        trackerService.addTracker(tracker);
        return ResponseEntity.ok("Tracker added successfully");
    }
}
