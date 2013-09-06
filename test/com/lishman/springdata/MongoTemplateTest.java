package com.lishman.springdata;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;
import static org.springframework.data.mongodb.core.query.Update.update;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lishman.springdata.domain.Continent;
import com.lishman.springdata.domain.Country;
import com.lishman.springdata.domain.OlympicMedals;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;

@Configuration
@ContextConfiguration(classes={MongoConfig.class})
@ActiveProfiles("mongodb")
@RunWith(SpringJUnit4ClassRunner.class)
public class MongoTemplateTest {

    @Autowired MongoTestData testData;
    @Autowired MongoOperations mongoOps;
    
    @Before
    public void reset() {
        testData.countriesTestData();
    }

    //------------------------------------------------- insert

    @Test
    public void testInsert() {
        OlympicMedals finland = new OlympicMedals("Finland", 101, 84, 117);

        mongoOps.insert(finland);

        displayDocumentsFromCursor(mongoOps.getCollection("medals"));
    }
    
    //------------------------------------------------- update

    @Test
    public void testUpdate() {
        
        mongoOps.updateFirst(query(where("name").is("Serbia")), update("population", 7120777L), Country.class);

        displayDocumentsFromCursor(mongoOps.getCollection("countries"));
    }
    
    //------------------------------------------------- delete
    
    @Test
    public void testDelete() {
        
        mongoOps.remove(query(where("_id").is("3")), Continent.class);

        displayDocumentsFromCursor(mongoOps.getCollection("continents"));
    }

    
    
    
//    @Test
//    public void testCountries() {
//        
//        Country greece = mongoOps.findOne(query(where("name").is("Greece")), Country.class);
//        System.out.println(">> " + greece.getId() + ", " + greece.getAreaInSquareMiles());
//        
//        DBCollection countries = mongoOps.getCollection("countries");
//        
//        displayDocumentsFromCursor(countries);
//    }
//
//    //------------------------------------------------- cities
//
//    @Test
//    public void testCities() {
//        
//        DBCollection cities = mongoOps.getCollection("cities");
//        
//        displayDocumentsFromCursor(cities);
//        
//        // use a String for the key
//        City berlin = mongoOps.findById("3", City.class);
//        Country country = mongoOps.findById(berlin.getCountry().getId(), Country.class);
//        
//        System.out.printf("\nBerlin is in %s (%s) and its attractions are %s",country.getName(), country.getContinent().getName(), berlin.getAttractions());
//    }
//    
//    //------------------------------------------------- medals
//    
//    @Test
//    public void testMedals() {
//        
//        DBCollection medals = mongoOps.getCollection("medals");
//        
//        displayDocumentsFromCursor(medals);
//        
//        OlympicMedals hungary = mongoOps.findOne(query(where("country").is("Hungary")), OlympicMedals.class);
//        
//        System.out.println("Silver " + hungary.getMedalCount(OlympicMedals.MedalType.SILVER));
//    }
    
    //------------------------------------------------- display results

    private void displayDocumentsFromCursor(final DBCollection collection) {
        DBCursor cursor = collection.find();
        try {
            while(cursor.hasNext()) {
                System.out.println(cursor.next());
            }
            
        } catch (Exception e) {
            cursor.close();
        }
    }
    
    
    
}
