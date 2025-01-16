package org.example.trackerapi.service;

import org.example.trackerapi.model.Tracker;
import org.example.trackerapi.repository.TrackerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrackerServiceImpl implements TrackerService{

    @Autowired
    private TrackerRepository trackerRepository;

    @Override
    public Tracker addTracker(Tracker tracker) {
        return trackerRepository.save(tracker);
    }

    @Override
    public Tracker getTrackerById(long trackerId) {
        return trackerRepository.findTrackerById(trackerId);
    }

    @Override
    public Tracker getTrackerByAnimalId(long animalId) {
        return trackerRepository.findTrackerByAnimalId(animalId);
    }
}
