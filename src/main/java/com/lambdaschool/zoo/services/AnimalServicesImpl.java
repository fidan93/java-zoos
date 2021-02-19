package com.lambdaschool.zoo.services;

import com.lambdaschool.zoo.models.Animal;
import com.lambdaschool.zoo.repositories.AnimalRepository;
import com.lambdaschool.zoo.views.AnimalCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service(value = "animalServices")
public class AnimalServicesImpl implements AnimalServices
{
    @Autowired
    private AnimalRepository animalRepository;

    @Override
    public List<AnimalCount> getZooAnimalsCount()
    {
      List<AnimalCount> counts = animalRepository.getZooCounts();
        return counts;
    }

    @Override
    public Animal findAnimalById(long id)
    {
        Animal animal = animalRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Animal "+id+" not found"));

        return animal;
    }
}
