package org.example.trackerapi.service;

import org.example.trackerapi.model.GeoRead;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

public interface GeoReadService {
    void save(GeoRead geoRead);
    List<GeoRead> getByTrackerIdAndDateRange(long trackerId, String startDate, String endDate);
    List<GeoRead> getAllByAnimalIdAndDate(long animalId, LocalDate date);
    double calculateTotalDistance(List<GeoRead> geoReads);
    double calculateAverageTemperature(List<GeoRead> geoReads);
    int countAnimalsInShepherd(List<GeoRead> geoReads);
    Duration calculateIdleTime(List<GeoRead> geoReads);
}
