package com.lishman.springdata.dao;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
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

import static org.junit.Assert.assertThat;

@Configuration
@ContextConfiguration(classes={MongoConfig.class})
@ActiveProfiles({"mongodb", "dao"})
@RunWith(SpringJUnit4ClassRunner.class)
public class ContinentMongoDaoTest {

    @Autowired private ContinentDao continentDao;
    
    @Autowired private MongoTestData testData;
    @Autowired private MongoOperations mongoOps;
    
    @Before
    public void reset() {
        testData.countriesTestData();
    }
    
    //------------------------------------------------- find all

    @Test
    public void testFindAll() {

        List<Continent> continents = continentDao.findAll();

        assertThat(continents.size(), equalTo(7));
    }
    
    //------------------------------------------------- find one
    
    @Test
    public void testFindOne() {
        
        Continent continent = continentDao.findOne("3");
        
        assertThat(continent.getName(), equalTo("Europe"));
        
    }

    //------------------------------------------------- find by name
    
    @Test
    public void testFindByName() {
        
        Continent continent = continentDao.findByName("Asia");
        
        assertThat(continent.getId(), equalTo("2"));
        
    }

    //------------------------------------------------- save
    
    @Test
    public void testSave() {
        
        continentDao.save(new Continent("10", "another continent"));
        
        assertThat(mongoOps.findById("10", Continent.class).getName(), equalTo("another continent"));
        
    }

    //------------------------------------------------- remove
    
    @Test
    public void testRemove() {
        
        continentDao.remove(mongoOps.findById("1", Continent.class));
        
        assertThat(mongoOps.findById("1", Continent.class), equalTo(null));
        
    }
    
}
