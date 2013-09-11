package com.lishman.springdata.domain;

import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection="countries")
@TypeAlias("ctry")
public class Country extends AbstractDocument {

    // TODO @Indexed doesn't work
    @Indexed(name="countryName", unique=true)
    private String name;

    @Field("area")
    private Integer areaInSquareMiles;

    private Long population;

    private Continent continent;

    public Country(String name, Integer areaInSquareMiles, Long population, Continent continent) {
        setName(name);
        setAreaInSquareMiles(areaInSquareMiles);
        setPopulation(population);
        setContinent(continent);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAreaInSquareMiles(Integer area) {
        this.areaInSquareMiles = area;
    }

    public Integer getAreaInSquareMiles() {
        return areaInSquareMiles;
    }

    public void setPopulation(Long population) {
        this.population = population;
    }

    public Long getPopulation() {
        return population;
    }
    
    public void setContinent(Continent continent) {
        this.continent = continent;
    }

    public Continent getContinent() {
        return continent;
    }
    
    public String toString() {
        return this.getName();
    }

}
