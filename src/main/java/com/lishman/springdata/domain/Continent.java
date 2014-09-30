package com.lishman.springdata.domain;

import com.google.common.base.Objects;

public final class Continent {
    
    private final long id;
    private final String name;
    
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