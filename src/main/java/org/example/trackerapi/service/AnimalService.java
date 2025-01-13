package org.example.trackerapi.service;

import org.example.trackerapi.model.Animal;

import java.util.List;

public interface AnimalService {
    Animal addAnimal(Animal animal);
    Animal deleteAnimal(long animalId);
    Animal updateAnimal(Animal animal);
    Animal getAnimal(long animalId);
    List<Animal> getAnimals();
    Animal findAnimalById(long animalId);
}
