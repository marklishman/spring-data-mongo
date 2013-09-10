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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lishman.springdata.MongoTestData;
import com.lishman.springdata.config.MongoConfig;
import com.lishman.springdata.domain.Continent;
import com.lishman.springdata.domain.Country;

@Configuration
@ContextConfiguration(classes={MongoConfig.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class CountryRepositoryTest {

    @Autowired private CountryRepository countryRepo;
    
    @Autowired private MongoTestData testData;
    @Autowired private MongoOperations mongoOps;
    
    @Before
    public void reset() {
        testData.countriesTestData();
    }

    //------------------------------------------------- find by name
    
    @Test
    public void testFindByName() {
        
        Country japan = countryRepo.findByName("Japan");
        
        assertThat(japan.getName(), equalTo("Japan"));
        assertThat(japan.getContinent().getName(), equalTo("Asia"));
        
    }
    
}
