package io.lishman.springdata.repository;

import io.lishman.springdata.domain.Continent;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ContinentRepository extends MongoRepository<Continent, Long> {
    
    public Continent findByName(String name);

    public List<Continent> findByNameStartingWithIgnoreCase(String start);
    
}