package org.example.trackerapi.service;

import org.example.trackerapi.model.Animal;

import java.util.List;

public interface AnimalService {
    void addAnimal(Animal animal);
    void deleteAnimal(long animalId);
    Animal updateAnimal(Animal animal);
    Animal getAnimal(long animalId);
    List<Animal> getAnimals();
    Animal findAnimalById(long animalId);
}
