package com.lishman.springdata.domain;

import org.springframework.data.annotation.PersistenceConstructor;

public class Megacity {

    private String name;
    private Integer population; 
    private Double growth; 
   
    @PersistenceConstructor
    public Megacity(String name, Integer population, Double growth) {
        this.setName(name);
        this.setPopulation(population);
        this.setGrowth(growth);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public long getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

    public double getGrowth() {
        return growth;
    }

    public void setGrowth(Double growth) {
        this.growth = growth;
    }
    
    public String toString() {
        return getName();
    }
}
