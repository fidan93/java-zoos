package com.lambdaschool.zoo.controllers;

import com.lambdaschool.zoo.models.Zoo;
import com.lambdaschool.zoo.services.ZooServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/zoos")
public class ZooControllers
{
    @Autowired
    private ZooServices zooServices;

    @GetMapping(value = "/zoos",produces = "application/json")
    public ResponseEntity<?> findAllZoo()
    {
       List<Zoo> zoos = zooServices.findAllZoos();
       return new ResponseEntity<>(zoos,
           HttpStatus.OK);
    }

    @GetMapping(value = "/zoo/{zooid}",produces = "application/json")
    public ResponseEntity<?> findZooById(@PathVariable long zooid)
    {
       Zoo zoo = zooServices.findById(zooid);
       return new ResponseEntity<>(zoo,HttpStatus.OK);
    }

    @PostMapping(value = "/zoo",consumes = "application/json",produces = "application/json")
    public ResponseEntity<?> addZoo(@Valid @RequestBody Zoo zoo)
    {
       zoo.setZooid(0);
       zoo = zooServices.save(zoo);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newZooURI = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{zooid}")
            .buildAndExpand(zoo.getZooid())
            .toUri();
        responseHeaders.setLocation(newZooURI);

        return new ResponseEntity<>(zoo,responseHeaders,HttpStatus.CREATED);
    }

    @PutMapping(value= "/zoo/{zooid}",produces = "application/json",consumes = "application/json")
    public ResponseEntity<?> replaceZooById(@Valid @RequestBody Zoo zoo,@PathVariable long zooid)
    {
        zoo.setZooid(zooid);
        zoo = zooServices.save(zoo);

        return new ResponseEntity<>(zoo,HttpStatus.OK);
    }

    @PatchMapping(value="/zoo/{zooid}",produces="application/json",consumes = "application/json")
    public ResponseEntity<?> updateZoo(@PathVariable long zooid,@RequestBody Zoo zoo)
    {
        zoo.setZooid(zooid);
        zoo = zooServices.update(zooid,zoo);
        return new ResponseEntity<>(zoo,HttpStatus.OK);
    }
}
