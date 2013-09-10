package com.lishman.springdata.repository;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

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
import com.lishman.springdata.domain.OlympicMedals;
import com.lishman.springdata.domain.OlympicMedals.MedalType;

@Configuration
@ContextConfiguration(classes={MongoConfig.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class MedalsRepositoryTest {

    @Autowired private MedalsRepository medalsRepo;
    
    @Autowired private MongoTestData testData;
    @Autowired private MongoOperations mongoOps;
    
    @Before
    public void reset() {
        testData.countriesTestData();
    }
    
    //------------------------------------------------- medal totals

    @Test
    public void testSilverCountReturnsCorrectNumber() {

        int silverMedals = medalsRepo.getSilverCount("China");

        assertThat(silverMedals, equalTo(144));
    }
    
    //------------------------------------------------- find country by name
    
    @Test
    public void testFindByCountryNameReturnsCorrectDocument() {
        
        OlympicMedals medals = medalsRepo.findByCountryName("China");
        
        assertThat(medals.getMedalCount(MedalType.GOLD), equalTo(201));
        
    }
}
