package com.lishman.springdata.domain;

import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;



@Document(collection="continents")
@TypeAlias("cont")
public class Continent extends AbstractDocument {

    // TODO @Indexed doesn't work
    @Indexed(name="continentName", unique=true)
    private String name;
    
    public Continent(String id, String name) {
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
