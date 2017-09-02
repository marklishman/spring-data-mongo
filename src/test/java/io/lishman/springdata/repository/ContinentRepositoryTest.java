package io.lishman.springdata.repository;

import io.lishman.springdata.TestData;
import io.lishman.springdata.config.MongoConfig;
import io.lishman.springdata.domain.Continent;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@ContextConfiguration(classes={MongoConfig.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class ContinentRepositoryTest {

    @Autowired private ContinentRepository continentRepo;
    @Autowired private MongoOperations mongoOps;
    
    @Before
    public void reset() {
        TestData.continents();
    }
    
    //------------------------------------------------- delete by id
    
    @Test
    public void testDeleteUsingId() {

        continentRepo.delete(3L);
        
        assertThat(mongoOps.findById(3L, Continent.class), is(equalTo(null)));
    }
    
    //------------------------------------------------- delete by object
    
    @Test
    public void testDeleteUsingObject() {
        Continent asia = mongoOps.findOne(query(where("name").is("Asia")), Continent.class);
        
        continentRepo.delete(asia);
        
        assertThat(mongoOps.findById(2L, Continent.class), is(equalTo(null)));
    }
    
   //------------------------------------------------- exists
    
    @Test
    public void testExists() {
        assertThat(continentRepo.exists(2L), is(equalTo(true)));
        assertThat(continentRepo.exists(22L), is(equalTo(false)));
    }

    //------------------------------------------------- findOne
    
    @Test
    public void testFindOne() {
        Continent asia = continentRepo.findOne(2L);
        assertThat(asia.getName(), is(equalTo("Asia")));
    }
    
    //------------------------------------------------- save
    
    @Test
    public void testSave() {
        Continent another = new Continent(10L, "Another");
        
        Continent continentInserted = continentRepo.save(another);
        
        assertThat(continentInserted.getName(), is(equalTo("Another")));
        assertThat(mongoOps.findById(10L, Continent.class).getName(), is(equalTo("Another")));
    }

}