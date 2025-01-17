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
    @Query("SELECT g FROM GeoReadSummary g WHERE g.animalType = :animalType AND g.date BETWEEN :startDate AND :endDate")
    List<GeoReadSummary> findByDateRangeAndAnimalType(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate, @Param("animalType") String animalType);
    @Query("SELECT g FROM GeoReadSummary g WHERE g.animalId = :animalId AND g.date BETWEEN :startDate AND :endDate")
    List<GeoReadSummary> findByDateRangeAndAnimalId(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate, long animalId);
    GeoReadSummary findGeoReadSummaryByDateAndAnimalId(LocalDate date, long animalId);
}
