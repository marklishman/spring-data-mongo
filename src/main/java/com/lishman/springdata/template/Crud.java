package com.lishman.springdata.template;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;
import static org.springframework.data.mongodb.core.query.Update.update;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.lishman.springdata.config.MongoConfig;
import com.lishman.springdata.domain.Continent;
import com.lishman.springdata.domain.Country;

@Component
public class Crud {
    
    // TODO MongoOperations interface backed by a MongoTemplate implementation (page 94)
    /*  - Resource management
     *  - Exception translation
     *  
     *  - General purpose, high level methods 
     *  - Low-level, callback driven methods
     *  page 95
     *  
     *  Implementing a DAO with MongoOperations (page 94)
     */
    @Autowired private MongoOperations mongoOps;
    
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(MongoConfig.class);
        Crud crud = ctx.getBean(Crud.class);
        crud.useMongoTemplateForCrud();
        ctx.close();
    }
    
    private void useMongoTemplateForCrud() {

        //------------------------------------------------- create

        if (mongoOps.collectionExists(Country.class)) {
            mongoOps.dropCollection(Country.class);
        }
        
        Country vietnam = new Country("Vietnam", 128565, 90388000L, new Continent(2, "Asia"));
        mongoOps.insert(vietnam);

        Country[] countries = new Country[] {
            new Country("Greece", 50949, 11257285L, new Continent(3, "Europe")),
            new Country("Iceland", 39770, 321857L, new Continent(3, "Europe")),
            new Country("New Zealand", 104454, 4320300L, new Continent(6, "Australia")),
            new Country("Serbia", 34116, 7120666L, new Continent(3, "Europe"))
        };
        
        mongoOps.insert(Arrays.asList(countries), Country.class);
        
        //------------------------------------------------- read
        
        List<Country> allCountries = mongoOps.findAll(Country.class);
        
        Country newZealand = mongoOps.findById(allCountries.get(2).getId(), Country.class);
        
        Query europeanQuery = query(where("continent.name").is("Europe"));
        List<Country> europeanCountries = mongoOps.find(europeanQuery, Country.class);
        
        Country greece = mongoOps.findOne(query(where("name").is("Greece")), Country.class);
        
        //------------------------------------------------- update
        
        Query query = query(where("name").is("Serbia"));
        Update update = update("population", 7120777L);
        mongoOps.updateFirst(query, update, Country.class);
        
        //------------------------------------------------- delete
        
        mongoOps.remove(query(where("_id").is("3")), Continent.class);
    }

}
