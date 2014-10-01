package com.lishman.springdata.repository;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lishman.springdata.TestData;
import com.lishman.springdata.config.MongoConfig;
import com.lishman.springdata.domain.Ocean;

@ContextConfiguration(classes={MongoConfig.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class OceanRepositoryTest {

    @Autowired private OceanRepository oceanRepo;
    
    @Rule public ExpectedException exception = ExpectedException.none();  
    
    @Before
    public void reset() {
        TestData.oceans();
    }

    //------------------------------------------------- find all to list
    
    @Test
    public void testFindAllToList() {
        List<Ocean> oceans = oceanRepo.findAll();
        assertThat(oceans.toString(), equalTo("[Arctic, Atlantic, Indian, Pacific, Southern]"));
    }
    
    //------------------------------------------------- sort all to list
    
    @Test
    public void testSortingAllToList() {
        Sort sortDescending = new Sort(Direction.DESC, "area");
        List<Ocean> oceans = oceanRepo.findAll(sortDescending);
        assertThat(oceans.toString(), equalTo("[Pacific, Atlantic, Indian, Southern, Arctic]"));
    }
    
    //------------------------------------------------- duplicate
    
    @Test
    public void testDuplicateNameCannotBeInserted() {
        exception.expect(DuplicateKeyException.class);
        
        Ocean arctic = new Ocean("Arctic", 123456);
        oceanRepo.save(arctic);
    }
}