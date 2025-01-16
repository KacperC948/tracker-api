package org.example.trackerapi.service;

import org.example.trackerapi.model.Animal;

import java.util.List;

public interface AnimalService {
    void addAnimal(Animal animal);
    void deleteAnimal(long animalId);
    void updateAnimal(Animal animal);
    Animal getAnimal(long animalId);
    List<Animal> getAnimals();
    List<Animal> getAllAnimalsByType(String type);
    Animal findAnimalById(long animalId);
}
