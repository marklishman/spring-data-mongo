package io.lishman.springdata.repository;

import java.math.BigInteger;

import org.springframework.data.repository.CrudRepository;

import io.lishman.springdata.domain.City;

public interface CityRepository extends CrudRepository<City, BigInteger> {

    public City findByName(String name);
    
}
