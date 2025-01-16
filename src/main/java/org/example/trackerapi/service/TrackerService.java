package org.example.trackerapi.service;

import org.example.trackerapi.model.Tracker;

import java.util.List;

public interface TrackerService {
    void addTracker(Tracker tracker);
    Tracker getTrackerById(long trackerId);
    Tracker getTrackerByAnimalId(long animalId);
    List<Tracker> getTrackers();
}
