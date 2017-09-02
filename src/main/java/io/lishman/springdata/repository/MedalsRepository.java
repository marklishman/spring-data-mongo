package io.lishman.springdata.repository;

import io.lishman.springdata.domain.OlympicMedals;
import org.springframework.data.repository.Repository;

import java.math.BigInteger;

public interface MedalsRepository extends Repository<OlympicMedals, BigInteger>,
                                          MedalsRepositoryCustom {
    
    public OlympicMedals findByCountryName(String name);

}
