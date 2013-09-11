package com.lishman.springdata.repository;


import java.math.BigInteger;

import org.springframework.stereotype.Repository;

import com.lishman.springdata.domain.Continent;

@Repository
public interface ContinentRepository extends SingleItemOnlyRepository<Continent, BigInteger> {
    
}
