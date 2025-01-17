package org.example.trackerapi.service;

import org.example.trackerapi.model.GeoRead;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface GeoReadService {
    void save(GeoRead geoRead);
    List<GeoRead> getByTrackerIdAndDateRange(long trackerId, String startDate, String endDate);
    List<GeoRead> getByDateRange(LocalDateTime startDate, LocalDateTime endDate);
    List<GeoRead> getAllByDateRangeAndAnimalId(LocalDateTime startDate, LocalDateTime endDate, long animalId);
    List<GeoRead> getAllByAnimalIdAndDate(long animalId, LocalDate date);
    double calculateTotalDistance(List<GeoRead> geoReads);
    double calculateAverageTemperature(List<GeoRead> geoReads);
    int countAnimalsInShepherd(List<GeoRead> geoReads);
    long calculateIdleTime(List<GeoRead> geoReads);
    List<GeoRead> getAllWarnings();
    GeoRead findGeoReadById(long id);
}
