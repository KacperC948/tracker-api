package org.example.trackerapi.repository;

import org.example.trackerapi.model.Animal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnimalRepository extends JpaRepository<Animal, Long> {
    Animal findAnimalById(long id);
    List<Animal> findAllByType(String type);
}
