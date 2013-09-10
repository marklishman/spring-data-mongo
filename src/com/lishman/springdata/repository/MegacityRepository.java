package com.lishman.springdata.repository;

import java.math.BigInteger;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.lishman.springdata.domain.Country;
import com.lishman.springdata.domain.MegacityCountry;

public interface MegacityRepository extends PagingAndSortingRepository<MegacityCountry, BigInteger> {

    public Country findByName(String name);
    
}
