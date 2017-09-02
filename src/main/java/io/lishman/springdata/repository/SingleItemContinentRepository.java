package io.lishman.springdata.repository;

import io.lishman.springdata.domain.Continent;

import java.math.BigInteger;

public interface SingleItemContinentRepository 
            extends SingleItemOnlyRepository<Continent, BigInteger> {
    
}
