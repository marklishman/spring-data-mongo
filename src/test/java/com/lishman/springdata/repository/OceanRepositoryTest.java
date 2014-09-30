package com.lishman.springdata.repository;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lishman.springdata.config.MongoConfig;
import com.lishman.springdata.domain.Ocean;
import com.lishman.springdata.testdata.TestData;

@ContextConfiguration(classes={MongoConfig.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class OceanRepositoryTest {

    @Autowired private OceanRepository oceanRepo;
    @Autowired private MongoOperations mongoOps;
    
    @Before
    public void reset() {
        TestData.continents();
    }

    //------------------------------------------------- find all to list
    
    @Test
    public void testFindAllToList() {
        
        List<Ocean> oceans = oceanRepo.findAll();
        
        assertThat(oceans.toString(), equalTo("[Artic, Atlantic, Indian, Pacific, Southern]"));
    }
    
    //------------------------------------------------- sort all to list
    
    @Test
    public void testSortingAllToList() {

        Sort sortDescending = new Sort(Direction.DESC, "area");
        List<Ocean> oceans = oceanRepo.findAll(sortDescending);
        
        assertThat(oceans.toString(), equalTo("[Pacific, Atlantic, Indian, Southern, Artic]"));

    }

}