package org.example.trackerapi.repository;

import org.example.trackerapi.model.GeoRead;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface GeoReadRepository extends JpaRepository<GeoRead, Long> {
    GeoRead findGeoReadById(long id);
    @Query("SELECT g FROM GeoRead g WHERE g.trackerId = :trackerId AND g.createdDate BETWEEN :startDate AND :endDate")
    List<GeoRead> findByTrackerIdAndDateRange(@Param("trackerId") long trackerId, @Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);
    @Query("SELECT g FROM GeoRead g WHERE g.animalId = :trackerId AND g.createdDate BETWEEN :startDate AND :endDate")
    List<GeoRead> findAllAnimalIdAndDateRange(@Param("animalId") long animalId, @Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);
}
