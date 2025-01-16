package org.example.trackerapi.controller;


import lombok.RequiredArgsConstructor;
import org.example.trackerapi.dto.TrackerDto;
import org.example.trackerapi.model.Tracker;
import org.example.trackerapi.requestBody.AssignAnimalToTrackerBody;
import org.example.trackerapi.service.TrackerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
                .name(trackerDto.getName())
                .build();

        trackerService.addTracker(tracker);
        return ResponseEntity.ok("Tracker added successfully");
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(trackerService.getTrackersWithAssignedAnimal());
    }

    @PostMapping("/assignAnimal")
    public ResponseEntity<String> assignAnimal(
            @RequestBody AssignAnimalToTrackerBody assignAnimalToTrackerBody
    ) {
        Tracker tracker = trackerService.getTrackerById(assignAnimalToTrackerBody.getTrackerId());
        tracker.setAnimalId(assignAnimalToTrackerBody.getAnimalId());

        trackerService.addTracker(tracker);
        return ResponseEntity.ok("Animal assigned to tracker successfully");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(
            @PathVariable long id,
            @RequestBody TrackerDto trackerDto
    ) {
        Tracker tracker = Tracker.builder()
                .id(id)
                .animalId(trackerDto.getAnimalId())
                .name(trackerDto.getName())
                .build();

        trackerService.addTracker(tracker);
        return ResponseEntity.ok("Tracker updated successfully");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(
            @PathVariable long id
    ) {
        trackerService.deleteTracker(id);
        return ResponseEntity.ok("Tracker deleted successfully");
    }

    @GetMapping("/getAllFree")
    public ResponseEntity<List<Tracker>> getAllFree() {
        return ResponseEntity.ok(trackerService.getTrackersWithoutAssignedAnimal());
    }
}
