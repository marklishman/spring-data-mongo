package io.lishman.springdata.repository;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import io.lishman.springdata.TestData;
import io.lishman.springdata.config.MongoConfig;
import io.lishman.springdata.domain.OlympicMedals;
import io.lishman.springdata.domain.OlympicMedals.MedalType;

@ContextConfiguration(classes={MongoConfig.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class MedalsRepositoryTest {

    @Autowired private MedalsRepository medalsRepo;
    
    @Before
    public void reset() {
        TestData.medals();
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
