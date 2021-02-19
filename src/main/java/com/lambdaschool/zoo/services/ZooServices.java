package com.lambdaschool.zoo.services;

import com.lambdaschool.zoo.models.Zoo;

import java.util.List;

public interface ZooServices
{
    List<Zoo> findAllZoos();
    Zoo findById(long id);

    Zoo save(Zoo zoo);
    Zoo update(long id,Zoo zoo);
}
