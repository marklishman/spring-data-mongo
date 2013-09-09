package com.lishman.springdata.domain;

import java.math.BigInteger;

import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="continents")
@TypeAlias("cont")
public class Continent extends AbstractDocument {

    @Indexed(name="continentName", unique=true)
    private String name;
    
    public Continent(int id, String name) {
        this(BigInteger.valueOf(id), name);
    }

    @PersistenceConstructor
    public Continent(BigInteger id, String name) {
        setId(id);
        setName(name);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    
    public String toString() {
        return getName();
    }
}
