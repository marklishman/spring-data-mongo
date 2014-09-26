package com.lishman.springdata.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.google.common.base.Objects;

@Document
public final class Continent {

    @Id private long id;
    
    private String name;
    
    public Continent(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    
    public String toString() {
        return Objects.toStringHelper(this)
                .add("id", id)
                .add("name", this.getName())
                .toString();
    }
}