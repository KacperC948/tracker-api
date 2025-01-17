package org.example.trackerapi.service;

import org.antlr.v4.runtime.misc.Pair;
import org.example.trackerapi.model.GeoReadSummary;
import org.example.trackerapi.repository.GeoReadSummaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        return aggregateGeoReadSummaries(geoReadSummaryRepository.findByDateRangeAndAnimalType(startDate, endDate, animalType));
    }

    @Override
    public List<GeoReadSummary> getByDateRangeAndAnimalId(LocalDate startDate, LocalDate endDate, long animalId) {
        return geoReadSummaryRepository.findByDateRangeAndAnimalId(startDate, endDate, animalId);
    }

    public static List<GeoReadSummary> aggregateGeoReadSummaries(List<GeoReadSummary> summaries) {
        Map<Object, List<GeoReadSummary>> groupedByDateAndType = summaries.stream()
                .collect(Collectors.groupingBy(summary ->
                        new Pair<>(summary.getDate(), summary.getAnimalType())
                ));

        List<GeoReadSummary> aggregatedSummaries = new ArrayList<>();
        for (Map.Entry<Object, List<GeoReadSummary>> entry : groupedByDateAndType.entrySet()) {
            List<GeoReadSummary> groupedSummaries = entry.getValue();
            if (!groupedSummaries.isEmpty()) {
                LocalDate date = groupedSummaries.getFirst().getDate();
                String animalType = groupedSummaries.getFirst().getAnimalType();
                double totalDistance = groupedSummaries.stream().mapToDouble(GeoReadSummary::getDistance).sum();
                double averageTemp = groupedSummaries.stream().mapToDouble(GeoReadSummary::getTempAvg).average().orElse(0);
                int totalOutOfShepherdCounter = groupedSummaries.stream().mapToInt(GeoReadSummary::getOutOfShepherdCounter).sum();
                long totalIdleTime = groupedSummaries.stream().mapToLong(GeoReadSummary::getIdleTime).sum();

                aggregatedSummaries.add(new GeoReadSummary(
                        0, 0, date, totalDistance, averageTemp, totalOutOfShepherdCounter, totalIdleTime, animalType)
                );
            }
        }

        return aggregatedSummaries;
    }
}
