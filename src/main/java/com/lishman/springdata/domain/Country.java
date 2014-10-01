package com.lishman.springdata.domain;

import java.math.BigInteger;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

// TODO @Document
/* Optional.
 * Allows customization.
 * Specify name of collection.
 * Scan for docs to be persisted.
 * Mapping info set up during init.
 * Slight performance benefit.
 */
@Document(collection="countries")
@TypeAlias("ctry")
public class Country {
    
    // TODO immutable object
    
    // TODO @Id - optional
    @Id private BigInteger id;

    private String name;

    @Field("area")
    private Integer areaInSquareMiles;

    // TODO @Field 
    /* Optional.
     * Allows customization.
     * Property is mapped to a field in a DBObject.
     * Name is duplicated in each document.
     * Allows a shorter name to be used.
     */
    @Field("pop")
    private Long population;

    // TODO Nested document
    /* This document (Country) is the Aggregate Root.
     * Subdocuments (Continent) must be accessed via the aggregate root.
     * Insert is an atomic operation.
     * Prejoined data. 
     */
    private Continent continent;

    public Country(String name, Integer areaInSquareMiles, Long population, Continent continent) {
        setName(name);
        setAreaInSquareMiles(areaInSquareMiles);
        setPopulation(population);
        setContinent(continent);
    }
    
    public BigInteger getId() {
        return id;
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
