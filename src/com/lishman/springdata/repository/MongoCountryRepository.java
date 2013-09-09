package com.lishman.springdata.repository;

import java.math.BigInteger;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.lishman.springdata.domain.Country;

public interface MongoCountryRepository extends PagingAndSortingRepository<Country, BigInteger> {

    public Country findByName(String name);
    
}
