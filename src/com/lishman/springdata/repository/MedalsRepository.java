package com.lishman.springdata.repository;

import java.math.BigInteger;

import org.springframework.data.repository.Repository;

import com.lishman.springdata.domain.OlympicMedals;

/* The repository interface extends both the custom
 * interface and the Repository interface.
 * 
 * Repository methods are declared here.
 */

public interface MedalsRepository extends Repository<OlympicMedals, BigInteger>,
                                          MedalsRepositoryCustom {

    public OlympicMedals findByCountryName(String name);

}
