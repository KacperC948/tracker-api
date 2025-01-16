package org.example.trackerapi.service;

import org.example.trackerapi.model.AnimalShepherd;

import java.util.List;

public interface AnimalShepherdService {
    List<AnimalShepherd> getAllByType(String type);
    AnimalShepherd save(AnimalShepherd animalShepherd);
    List<AnimalShepherd> saveAll(List<AnimalShepherd> animalShepherds);
    void deleteByAnimalType(String type);
}
