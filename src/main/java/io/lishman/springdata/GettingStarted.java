package io.lishman.springdata;

import io.lishman.springdata.config.MongoConfig;
import io.lishman.springdata.domain.Continent;
import io.lishman.springdata.repository.ContinentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class GettingStarted {
    
    @Autowired private MongoOperations mongoOps;
    
    public static void main(String[] args) {
        
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(MongoConfig.class);
        
        ContinentRepository repo = ctx.getBean(ContinentRepository.class);
        
        //------------------------------------------------- CRUD
        
        Continent[] continents = new Continent[] {
            new Continent(1, "Africa"),
            new Continent(2, "Asia"),
            new Continent(3, "Europe"),
            new Continent(4, "North America"),
            new Continent(5, "South America"),
            new Continent(6, "Australia")
        };
        
        // save multiple entities
        repo.save(Arrays.asList(continents));
        
        // check if an entity exists
        if (! repo.exists(7L)) {
            // save a single entity
            Continent antartica = new Continent(7, "Antartica");
            repo.save(antartica);
        }

        // count the number of entities
        long count = repo.count();
        
        // get all the entities
        Iterable<Continent> allContinents = repo.findAll();
        
        // get a single entity
        Continent asia = repo.findOne(2L);
        
        // delete an entity
        repo.delete(asia);
        
        //------------------------------------------------- Query Methods

        // get continent where name is 'Europe'
        Continent europe = repo.findByName("Europe");

        // get continents where name begins with 'a' or 'A'
        List<Continent> aContinents = repo.findByNameStartingWithIgnoreCase("a");

        ctx.close();
    }

}
