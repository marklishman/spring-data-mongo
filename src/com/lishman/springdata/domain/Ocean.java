package com.lishman.springdata.domain;

import java.math.BigInteger;

import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="oceans")
@TypeAlias("ocean")
public class Ocean extends AbstractDocument {

    private String name;
    private int area;
    
    public Ocean(int id, String name, int area) {
        this(BigInteger.valueOf(id), name, area);
    }

    @PersistenceConstructor
    public Ocean(BigInteger id, String name, int area) {
        setId(id);
        setName(name);
        setArea(area);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    
    public void setArea(int area) {
        this.area = area;
    }
    
    public int getarea() {
        return area;
    }
    
    public String toString() {
        return getName();
    }
}
