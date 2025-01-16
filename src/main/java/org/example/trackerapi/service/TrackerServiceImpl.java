package org.example.trackerapi.service;

import org.example.trackerapi.model.Tracker;
import org.example.trackerapi.repository.TrackerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrackerServiceImpl implements TrackerService{

    @Autowired
    private TrackerRepository trackerRepository;

    @Override
    public void addTracker(Tracker tracker) {
        trackerRepository.save(tracker);
    }

    @Override
    public Tracker getTrackerById(long trackerId) {
        return trackerRepository.findTrackerById(trackerId);
    }

    @Override
    public Tracker getTrackerByAnimalId(long animalId) {
        return trackerRepository.findTrackerByAnimalId(animalId);
    }

    @Override
    public List<Tracker> getTrackers() {
        return trackerRepository.findByAnimalIdNot(0);
    }

    @Override
    public void deleteTracker(long trackerId) {
        trackerRepository.deleteById(trackerId);
    }
}
