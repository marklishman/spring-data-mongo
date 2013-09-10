package com.lishman.springdata.domain;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="megacities")
@TypeAlias("megacity")
public class MegacityCountry extends AbstractDocument {

    private String name;
    
    private List<Megacity> cities;

    public MegacityCountry(BigInteger id, String name, List<Megacity> cities) {
        this.setId(id);
        this.setName(name);
        this.setCities(cities);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<Megacity> getCities() {
        return cities;
    }

    public void setCities(List<Megacity>cities) {
        this.cities = cities;
    }

    public String toString() {
        return getName();
    }
}
