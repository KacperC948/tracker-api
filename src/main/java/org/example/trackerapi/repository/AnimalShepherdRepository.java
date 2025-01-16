package org.example.trackerapi.repository;

import org.example.trackerapi.model.AnimalShepherd;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnimalShepherdRepository extends JpaRepository<AnimalShepherd, Long> {
    List<AnimalShepherd> findByAnimalType(String type);
    void deleteAllByAnimalType(String type);
}
