package com.lambdaschool.zoo.repositories;

import com.lambdaschool.zoo.models.Animal;
import com.lambdaschool.zoo.views.AnimalCount;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AnimalRepository extends CrudRepository<Animal,Long>
{

    @Query(value = "SELECT  a.animaltype ,a.animalid,count(z.zooid) as countzoos "+
    "FROM ZOOANIMALS z LEFT JOIN Animals a "+
    "ON z.animalid = a.animalid "+
    "GROUP BY a.animaltype",nativeQuery = true)
    List <AnimalCount> getZooCounts();
}
