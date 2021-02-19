package com.lambdaschool.zoo.services;


import com.lambdaschool.zoo.models.Animal;
import com.lambdaschool.zoo.views.AnimalCount;

import java.util.List;

public interface AnimalServices
{
  List<AnimalCount> getZooAnimalsCount();
  Animal findAnimalById(long id);
}
