package com.lishman.springdata.repository;

import java.math.BigInteger;

import org.springframework.data.repository.Repository;

import com.lishman.springdata.domain.Country;

public interface CountryRepository extends Repository<Country, BigInteger> {

    public Country findByName(String name);
    
}
