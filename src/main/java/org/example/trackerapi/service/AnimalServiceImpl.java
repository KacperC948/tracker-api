package org.example.trackerapi.service;

import org.example.trackerapi.model.Animal;
import org.example.trackerapi.repository.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnimalServiceImpl implements AnimalService {

    @Autowired
    private AnimalRepository animalRepository;

    @Override
    public void addAnimal(Animal animal) {
        animalRepository.save(animal);
    }

    @Override
    public void deleteAnimal(long animalId) {
        Optional<Animal> animal = animalRepository.findById(animalId);
        animal.ifPresent(value -> animalRepository.delete(value));
    }

    @Override
    public Animal updateAnimal(Animal animal) {
        return animalRepository.save(animal);
    }

    @Override
    public Animal getAnimal(long animalId) {
        return animalRepository.findById(animalId).orElse(null);
    }

    @Override
    public List<Animal> getAnimals() {
        return animalRepository.findAll();
    }

    @Override
    public Animal findAnimalById(long animalId) {
        return animalRepository.findById(animalId).orElse(null);
    }

}