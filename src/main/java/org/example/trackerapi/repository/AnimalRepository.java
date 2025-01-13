package org.example.trackerapi.repository;

import org.example.trackerapi.model.Animal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalRepository extends JpaRepository<Animal, Long> {
    Animal findAnimalById(long id);
}
