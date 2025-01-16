package org.example.trackerapi.repository;

import org.example.trackerapi.model.Tracker;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TrackerRepository extends JpaRepository<Tracker, Long> {
    Tracker findTrackerById(long id);
    Tracker findTrackerByAnimalId(long animalId);
    List<Tracker> findByAnimalIdNot(long animalId);
}