package com.lishman.springdata;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.lishman.springdata.config.MongoConfig;
import com.lishman.springdata.domain.Continent;
import com.lishman.springdata.repository.ContinentRepository;
import com.lishman.springdata.testdata.GettingStartedData;

public class GettingStarted {
    
    public static void main(String[] args) {
        
        GettingStartedData.continentsTestData();
        
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(MongoConfig.class);
        ContinentRepository repo = ctx.getBean(ContinentRepository.class);
        
        //------------------------------------------------- CRUD

        // count the number of entities
        long count = repo.count();
        
        // get all the entities
        Iterable<Continent> allContinents = repo.findAll();
        
        // get a single entity
        Continent asia = repo.findOne(2L);
        
        // delete an entity
        repo.delete(asia);

        // check if an entity exists
        if (! repo.exists(2L)) {
            // save an entity
            repo.save(asia);
        }
        
        //------------------------------------------------- Query
        
        Continent europe = repo.findByName("Europe");

        List<Continent> continentsThatWithA = repo.findByNameStartingWithIgnoreCase("a");

        ctx.close();
    }

}
