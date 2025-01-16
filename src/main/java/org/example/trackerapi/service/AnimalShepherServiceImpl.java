package org.example.trackerapi.service;

import org.example.trackerapi.model.AnimalShepherd;
import org.example.trackerapi.repository.AnimalShepherdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnimalShepherServiceImpl implements AnimalShepherdService{

    @Autowired
    AnimalShepherdRepository animalShepherdRepository;


    @Override
    public List<AnimalShepherd> getAllByType(String type) {
        return animalShepherdRepository.findByAnimalType(type);
    }

    @Override
    public AnimalShepherd save(AnimalShepherd animalShepherd) {
        return animalShepherdRepository.save(animalShepherd);
    }

    @Override
    public void saveAll(List<AnimalShepherd> animalShepherds) {
        List<AnimalShepherd> result = new ArrayList<AnimalShepherd>();
        for (AnimalShepherd entity : animalShepherds) {
            result.add(save(entity));
        }
    }

    @Override
    public void deleteByAnimalType(String type) {
        animalShepherdRepository.deleteAllByAnimalType(type);
    }
}
