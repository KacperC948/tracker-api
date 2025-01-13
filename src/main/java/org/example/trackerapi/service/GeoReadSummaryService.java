package org.example.trackerapi.service;

import org.example.trackerapi.model.GeoReadSummary;

import java.time.LocalDate;
import java.util.List;

public interface GeoReadSummaryService {
    GeoReadSummary save(GeoReadSummary GeoReadSummary);
    GeoReadSummary getByDateAndAnimalId(LocalDate date, long animalId);
    List<GeoReadSummary> getByDateRange(LocalDate startDate, LocalDate endDate);
}
