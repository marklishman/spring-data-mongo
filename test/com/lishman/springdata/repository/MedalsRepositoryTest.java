package com.lishman.springdata.repository;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lishman.springdata.config.MongoConfig;
import com.lishman.springdata.domain.OlympicMedals;
import com.lishman.springdata.domain.OlympicMedals.MedalType;
import com.lishman.springdata.testdata.MongoTestData;

@ContextConfiguration(classes={MongoConfig.class})
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
public class MedalsRepositoryTest {

    @Autowired private MedalsRepository medalsRepo;
    
    @Autowired private MongoTestData testData;
    @Autowired private MongoOperations mongoOps;
    
    @Before
    public void reset() {
        testData.countriesTestData();
    }
    
    //------------------------------------------------- manual implementation

    @Test
    public void testMedalCountReturnsCorrectNumber() {
        assertThat(medalsRepo.getBronzeCount("China"), equalTo(128));
        assertThat(medalsRepo.getSilverCount("East Germany"), equalTo(129));
        assertThat(medalsRepo.getGoldCount("Italy"), equalTo(198));
    }
    
    //------------------------------------------------- spring implementation
    
    @Test
    public void testFindByCountryNameReturnsCorrectDocument() {
        OlympicMedals medals = medalsRepo.findByCountryName("China");
        assertThat(medals.getMedalCount(MedalType.GOLD), equalTo(201));
    }
}
