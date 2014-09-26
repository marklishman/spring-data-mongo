package com.lishman.springdata.repository;

import java.math.BigInteger;

import com.lishman.springdata.domain.Continent;

public interface SingleItemContinentRepository 
            extends SingleItemOnlyRepository<Continent, BigInteger> {
    
}
