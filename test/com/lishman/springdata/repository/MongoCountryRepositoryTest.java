package com.lishman.springdata.repository;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import java.math.BigInteger;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lishman.springdata.MongoConfig;
import com.lishman.springdata.MongoTestData;
import com.lishman.springdata.domain.Continent;
import com.lishman.springdata.domain.Country;

@Configuration
@ContextConfiguration(classes={MongoConfig.class})
@ActiveProfiles({"mongodb", "repo"})
@RunWith(SpringJUnit4ClassRunner.class)
public class MongoCountryRepositoryTest {

    @Autowired private MongoCountryRepository countryRepo;
    
    @Autowired private MongoTestData testData;
    @Autowired private MongoOperations mongoOps;
    
    @Before
    public void reset() {
        testData.countriesTestData();
    }
    
    //------------------------------------------------- find all

    @Test
    public void testFindAll() {

        Iterable<Country> countries = countryRepo.findAll();

        assertThat(countries.iterator().next().getName(), equalTo("Australia"));
    }
    
    //------------------------------------------------- find one
    
    @Test
    public void testFindOne() {
        BigInteger australiaId = countryRepo.findAll().iterator().next().getId();
        
        Country country = countryRepo.findOne(australiaId);
        
        assertThat(country.getName(), equalTo("Australia"));
        
    }

    //------------------------------------------------- find by name
    
    @Test
    public void testFindByName() {
        
        Country japan = countryRepo.findByName("Japan");
        
        assertThat(japan.getName(), equalTo("Japan"));
        assertThat(japan.getContinent().getName(), equalTo("Asia"));
        
    }

    //------------------------------------------------- save
    
    @Test
    public void testSave() {
        
        Continent northAmerica = mongoOps.findById("4", Continent.class);
        
        Country cuba = countryRepo.save(new Country("Cuba", 42426, 11163934L, northAmerica));
        
        assertThat(mongoOps.findById(cuba.getId(), Country.class).getName(), equalTo("Cuba"));
        
    }

    //------------------------------------------------- remove
    
    @Test
    public void testRemove() {
        
        Country serbia = mongoOps.findOne(query(where("name").is("Serbia")), Country.class);
        BigInteger serbiaId = serbia.getId();
        
        countryRepo.delete(serbia);
        
        assertThat(mongoOps.findById(serbiaId, Continent.class), equalTo(null));
        
    }
    
}
