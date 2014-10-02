package com.lishman.springdata.repository;

import java.math.BigInteger;

import org.springframework.data.repository.Repository;

import com.lishman.springdata.domain.OlympicMedals;

public interface MedalsRepository extends Repository<OlympicMedals, BigInteger>,
                                          MedalsRepositoryCustom {
    
    public OlympicMedals findByCountryName(String name);

}
