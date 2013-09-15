package com.lishman.springdata.repository;

import java.math.BigInteger;

import com.lishman.springdata.domain.Continent;

public interface ContinentRepository 
            extends SingleItemOnlyRepository<Continent, BigInteger> {
    
}
