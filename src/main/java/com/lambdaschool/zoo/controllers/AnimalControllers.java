package com.lambdaschool.zoo.controllers;

import com.lambdaschool.zoo.services.AnimalServices;
import com.lambdaschool.zoo.views.AnimalCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AnimalControllers
{
    @Autowired
    private AnimalServices animalServices;

    @GetMapping(value = "/animals/count", produces = "application/json")
    public ResponseEntity<?> findZoosCount()
    {
        List <AnimalCount> count = animalServices.getZooAnimalsCount();
        return new ResponseEntity<>(count,
            HttpStatus.OK);
    }
}
