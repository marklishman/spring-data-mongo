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
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lishman.springdata.config.MongoConfig;
import com.lishman.springdata.domain.Continent;
import com.lishman.springdata.testdata.MongoTestData;

@ContextConfiguration(classes={MongoConfig.class})
@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
public class ContinentRepositoryTest {

    @Autowired private SingleItemContinentRepository continentRepo;
    
    @Autowired private MongoTestData testData;
    @Autowired private MongoOperations mongoOps;
    
    @Before
    public void reset() {
        testData.countriesTestData();
    }
    
    //------------------------------------------------- delete by id
    
    @Test
    public void testDeleteUsingId() {
        
        continentRepo.delete(BigInteger.valueOf(3));
        
        assertThat(mongoOps.findById(BigInteger.valueOf(3), Continent.class), equalTo(null));
    }
    
    //------------------------------------------------- delete by entity
    
    @Test
    public void testDeleteUsingEntity() {
        Continent asia = mongoOps.findOne(query(where("name").is("Asia")), Continent.class);
        
        continentRepo.delete(asia);
        
        assertThat(mongoOps.findById(BigInteger.valueOf(2), Continent.class), equalTo(null));
    }
    
   //------------------------------------------------- exists
    
    @Test
    public void testExists() {
        assertThat(continentRepo.exists(BigInteger.valueOf(2)), equalTo(true));
        assertThat(continentRepo.exists(BigInteger.valueOf(22)), equalTo(false));
    }

    //------------------------------------------------- findOne
    
    @Test
    public void testFindOne() {
        Continent asia = continentRepo.findOne(BigInteger.valueOf(2));
        assertThat(asia.getName(), equalTo("Asia"));
    }
    
    //------------------------------------------------- save
    
//    @Test
//    public void testInsert() {
//        Continent another = new Continent(BigInteger.valueOf(10), "Another");
//        
//        Continent continentInserted = continentRepo.save(another);
//        
//        assertThat(continentInserted.getName(), equalTo("Another"));
//        assertThat(mongoOps.findById(BigInteger.valueOf(10), Continent.class).getName(), equalTo("Another"));
//    }


}
