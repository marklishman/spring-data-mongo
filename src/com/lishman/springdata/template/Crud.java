package com.lishman.springdata.template;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;
import static org.springframework.data.mongodb.core.query.Update.update;

import java.math.BigInteger;
import java.net.UnknownHostException;
import java.util.List;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.lishman.springdata.MongoTestData;
import com.lishman.springdata.domain.Continent;
import com.lishman.springdata.domain.Country;
import com.mongodb.MongoClient;

public class Crud {
    
    public static void main(String[] args) throws UnknownHostException {
        
        MongoClient client = new MongoClient("mongo-host");
        MongoOperations mongoOps = new MongoTemplate(client, "world");
        
        MongoTestData testData = new MongoTestData();
        testData.setMongoOperations(mongoOps);
        testData.countriesTestData();
        
        //------------------------------------------------- create
        
        Country iceland = new Country("Iceland", 39770, 321857L, new Continent(3, "Europe"));
        mongoOps.insert(iceland);
        
        
        //------------------------------------------------- read
        
        List<Continent> continents = mongoOps.findAll(Continent.class);
        System.out.println(continents);
        
        Continent asia = mongoOps.findById(BigInteger.valueOf(2), Continent.class);
        System.out.println("Continent name is " + asia.getName());
        
        Country greece = mongoOps.findOne(query(where("name").is("Greece")), Country.class);
        System.out.printf("%s is in %s\n", greece.getName(), greece.getContinent().getName());
        
        Query europeanQuery = query(where("continent.name").is("Europe"));
        List<Country> europeanCountries = mongoOps.find(europeanQuery, Country.class);
        System.out.println("Countries in Europe: " + europeanCountries);
        
        
        //------------------------------------------------- update
        
        Query query = query(where("name").is("Serbia"));
        Update update = update("population", 7120777L);
        mongoOps.updateFirst(query, update, Country.class);

        
        //------------------------------------------------- delete
        
        mongoOps.remove(query(where("_id").is("3")), Continent.class);
        System.out.println(mongoOps.findAll(Continent.class));

    }


}
