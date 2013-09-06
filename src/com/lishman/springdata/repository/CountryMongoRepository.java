package com.lishman.springdata.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.lishman.springdata.domain.Country;

public interface CountryMongoRepository extends PagingAndSortingRepository<Country, String> {

    public Country findByName(String name);
    
}
