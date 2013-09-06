package com.lishman.springdata.dao;

import java.util.List;

import com.lishman.springdata.domain.Continent;

public interface ContinentDao {
    
    public List<Continent> findAll();

    public Continent findOne(String id);

    public Continent findByName(String name);

    public Continent save(Continent continent);

    public void remove(Continent continent);
}
