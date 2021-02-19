package com.lambdaschool.zoo.services;

import com.lambdaschool.zoo.models.Animal;
import com.lambdaschool.zoo.models.Telephone;
import com.lambdaschool.zoo.models.Zoo;
import com.lambdaschool.zoo.models.ZooAnimal;
import com.lambdaschool.zoo.repositories.ZooRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Transactional
@Service(value = "zooServices")
public class ZooServicesImpl implements ZooServices
{
    @Autowired
    private ZooRepository zooRepository;

    @Autowired
    private AnimalServices animalServices;

    @Transactional
    @Override
    public Zoo save(Zoo tempzoo)
    {
      Zoo newZoo = new Zoo();

      if(tempzoo.getZooid()!=0)
      {
          zooRepository.findById(tempzoo.getZooid())
              .orElseThrow(() -> new EntityNotFoundException("Zoo "+tempzoo.getZooid()+" not found"));
          newZoo.setZooid(tempzoo.getZooid());
      }
      newZoo.setZooname(tempzoo.getZooname().toLowerCase());

      newZoo.getAnimals().clear();
      for(ZooAnimal za : tempzoo.getAnimals())
      {
          Animal newanimal = animalServices.findAnimalById(za.getAnimal().getAnimalid());

          ZooAnimal zooAnimal = new ZooAnimal();
          zooAnimal.setIncomingzoo(za.getIncomingzoo());

          newZoo.getAnimals().add(new ZooAnimal(newZoo,newanimal, zooAnimal.getIncomingzoo()));
      }

      newZoo.getTelephones().clear();
      for(Telephone t:tempzoo.getTelephones())
      {
          newZoo.getTelephones()
              .add(new Telephone(t.getPhonetype(),t.getPhonenumber(),newZoo));
      }
        return zooRepository.save(newZoo);
    }

    @Override
    public List<Zoo> findAllZoos()
    {
        List<Zoo> zoolist = new ArrayList<>();
        zooRepository.findAll().iterator().forEachRemaining(zoolist::add);
        return zoolist;
    }

    @Override
    public Zoo findById(long id)
    {
        Zoo zoo = zooRepository.findById(id)
            .orElseThrow(()-> new EntityNotFoundException("Zoo "+id+ " not found"));
        return zoo;
    }

    @Override
    public Zoo update(long id, Zoo tempzoo)
    {
        Zoo newZoo = zooRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Zoo " + id + " not found"));

        if (tempzoo.getZooname() != null)
        {
            newZoo.setZooname(tempzoo.getZooname());
        }

        if (tempzoo.getAnimals().size() > 0)
        {
            newZoo.getAnimals().clear();
            for (ZooAnimal za : tempzoo.getAnimals())
            {
                Animal animal = animalServices.findAnimalById(za.getAnimal()
                    .getAnimalid());

                ZooAnimal zooAnimal = new ZooAnimal();
                if (za.getIncomingzoo() != null)
                {
                    zooAnimal.setIncomingzoo(za.getIncomingzoo());
                }
                newZoo.getAnimals()
                    .add(new ZooAnimal(newZoo,
                        animal,
                        zooAnimal.getIncomingzoo()));
            }
        }

        if (tempzoo.getTelephones().size() > 0)
        {
            newZoo.getTelephones().clear();
            for (Telephone t : tempzoo.getTelephones())
            {
                newZoo.getTelephones()
                    .add(new Telephone(t.getPhonetype(),
                        t.getPhonenumber(),
                        newZoo));
            }
        }
        return zooRepository.save(newZoo);
    }
}
