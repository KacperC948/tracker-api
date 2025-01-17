package org.example.trackerapi.service;

import org.example.trackerapi.model.GeoReadSummary;
import org.example.trackerapi.repository.GeoReadSummaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class GeoReadSummaryServiceImpl implements GeoReadSummaryService {

    @Autowired
    private GeoReadSummaryRepository geoReadSummaryRepository;

    @Override
    public GeoReadSummary save(GeoReadSummary geoReadSummary) {
        return geoReadSummaryRepository.save(geoReadSummary);
    }

    @Override
    public GeoReadSummary getByDateAndAnimalId(LocalDate date, long animalId) {
        return geoReadSummaryRepository.findGeoReadSummaryByDateAndAnimalId(date, animalId);
    }

    @Override
    public List<GeoReadSummary> getByDateRange(LocalDate startDate, LocalDate endDate) {
        return geoReadSummaryRepository.findByDateRange(startDate, endDate);
    }

    @Override
    public List<GeoReadSummary> getByDateRangeAndAnimalType(LocalDate startDate, LocalDate endDate, String animalType) {
        return geoReadSummaryRepository.findByDateRangeAndAnimalType(startDate, endDate, animalType);
    }

    @Override
    public List<GeoReadSummary> getByDateRangeAndAnimalId(LocalDate startDate, LocalDate endDate, long animalId) {
        return geoReadSummaryRepository.findByDateRangeAndAnimalId(startDate, endDate, animalId);
    }
}
