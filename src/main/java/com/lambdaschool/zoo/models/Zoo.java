package com.lambdaschool.zoo.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "zoos")
public class Zoo extends Auditable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long zooid;

    private String zooname;

    @OneToMany(mappedBy = "zoo",
    cascade = CascadeType.ALL,
    orphanRemoval = true)
    @JsonIgnoreProperties(value ="zoo",allowSetters = true)
    private List <Telephone> telephones = new ArrayList<>();

    @OneToMany(mappedBy = "zoo",
    cascade = CascadeType.ALL,
    orphanRemoval = true)
    @JsonIgnoreProperties(value = "zoo",allowSetters = true)
    private Set<ZooAnimal> animals = new HashSet<>();

    public Zoo()
    {
    }

    public Zoo(String name)
    {
        this.zooname = name;
    }

    public long getZooid()
    {
        return zooid;
    }

    public void setZooid(long zooid)
    {
        this.zooid = zooid;
    }

    public String getZooname()
    {

        return zooname;
    }

    public void setZooname(String name)
    {
        this.zooname = name;
    }

    public List<Telephone> getTelephones()
    {
        return telephones;
    }

    public void setTelephones(List<Telephone> telephones)
    {
        this.telephones = telephones;
    }

    public Set<ZooAnimal> getAnimals()
    {
        return animals;
    }

    public void setAnimals(Set<ZooAnimal> animals)
    {
        this.animals = animals;
    }


}
