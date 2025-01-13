package org.example.trackerapi.repository;

import org.example.trackerapi.model.GeoReadSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface GeoReadSummaryRepository extends JpaRepository<GeoReadSummary, Long> {
    GeoReadSummary findGeoReadSummaryById(long id);
    @Query("SELECT g FROM GeoReadSummary g WHERE g.date BETWEEN :startDate AND :endDate")
    List<GeoReadSummary> findByDateRange(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
    GeoReadSummary findGeoReadSummaryByDateAndAnimalId(LocalDate date, long animalId);
}
