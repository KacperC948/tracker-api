package org.example.trackerapi.service;

import org.example.trackerapi.model.Tracker;

public interface TrackerService {
    Tracker addTracker(Tracker tracker);
    Tracker getTrackerById(long trackerId);
    Tracker getTrackerByAnimalId(long animalId);
}
