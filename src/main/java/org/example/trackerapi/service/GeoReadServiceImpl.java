package org.example.trackerapi.service;

import org.example.trackerapi.model.GeoRead;
import org.example.trackerapi.repository.GeoReadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.function.Predicate;

@Service
public class GeoReadServiceImpl implements GeoReadService {

    private static final double EARTH_RADIUS_KM = 6371.0; // Promień Ziemi w kilometrach

    @Autowired
    private GeoReadRepository geoReadRepository;

    @Override
    public void save(GeoRead geoRead) {
        geoReadRepository.save(geoRead);
    }

    @Override
    public List<GeoRead> getByTrackerIdAndDateRange(long trackerId, String startDate, String endDate) {
        return geoReadRepository.findByTrackerIdAndDateRange(trackerId, LocalDateTime.parse(startDate), LocalDateTime.parse(endDate));
    }

    @Override
    public List<GeoRead> getByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        return geoReadRepository.findByDateRange(startDate, endDate);
    }

    @Override
    public List<GeoRead> getAllByAnimalIdAndDate(long animalId, LocalDate date) {
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.atTime(LocalTime.MAX);
        return geoReadRepository.findAllAnimalIdAndDateRange(animalId, startOfDay, endOfDay);
    }

    @Override
    public List<GeoRead> getAllWarnings() {
        return geoReadRepository.findAllByAnimalInShepherdConfirmedAndTempExceededConfirmed(false, false);
    }

    @Override
    public GeoRead findGeoReadById(long id) {
        return geoReadRepository.findGeoReadById(id);
    }

    public double calculateTotalDistance(List<GeoRead> geoReads) {
        if (geoReads == null || geoReads.size() < 2) {
            return 0.0;
        }

        double totalDistance = 0.0;

        for (int i = 1; i < geoReads.size(); i++) {
            GeoRead previous = geoReads.get(i - 1);
            GeoRead current = geoReads.get(i);

            totalDistance += calculateDistance(
                    previous.getLatitude(),
                    previous.getLongitude(),
                    current.getLatitude(),
                    current.getLongitude()
            );
        }

        return totalDistance;
    }

    private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        double lat1Rad = Math.toRadians(lat1);
        double lon1Rad = Math.toRadians(lon1);
        double lat2Rad = Math.toRadians(lat2);
        double lon2Rad = Math.toRadians(lon2);

        double deltaLat = lat2Rad - lat1Rad;
        double deltaLon = lon2Rad - lon1Rad;

        double a = Math.sin(deltaLat / 2) * Math.sin(deltaLat / 2) +
                Math.cos(lat1Rad) * Math.cos(lat2Rad) *
                        Math.sin(deltaLon / 2) * Math.sin(deltaLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return EARTH_RADIUS_KM * c;
    }

    public double calculateAverageTemperature(List<GeoRead> geoReads) {
        if (geoReads == null || geoReads.isEmpty()) {
            throw new IllegalArgumentException("The list of GeoRead cannot be null or empty");
        }

        double totalTemperature = 0.0;

        for (GeoRead geoRead : geoReads) {
            totalTemperature += geoRead.getCurrentTemp();
        }

        return totalTemperature / geoReads.size();
    }

    public int countAnimalsInShepherd(List<GeoRead> geoReads) {
        if (geoReads == null) {
            throw new IllegalArgumentException("The list of GeoRead cannot be null");
        }

        return (int) geoReads.stream()
                .filter(Predicate.not(GeoRead::isAnimalInShepherd))
                .count();
    }

    public Duration calculateIdleTime(List<GeoRead> geoReads) {
        if (geoReads == null || geoReads.size() < 2) {
            return Duration.ZERO;
        }

        Duration duration = Duration.ZERO;

        for (int i = 1; i < geoReads.size(); i++) {
            GeoRead previous = geoReads.get(i - 1);
            GeoRead current = geoReads.get(i);

            if (previous.getLatitude() == current.getLatitude() &&
                    previous.getLongitude() == current.getLongitude()) {

                duration = Duration.between(previous.getCreatedDate(), current.getCreatedDate());
            }
        }

        return duration;
    }
}
