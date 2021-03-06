package io.lishman.springdata.repository;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import io.lishman.springdata.TestData;
import io.lishman.springdata.config.MongoConfig;
import io.lishman.springdata.domain.State;

@ContextConfiguration(classes={MongoConfig.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class StateRepositoryTest {

    @Autowired private StateRepository stateRepo;
    
    @Before
    public void reset() {
        TestData.states();
    }
    
    //------------------------------------------------- paging to Page

    @Test
    public void testPagingToPage() {
        int page = 2;
        int pageSize = 5;
        
        Pageable pageable = new PageRequest(page, pageSize);
        Page<State> states = stateRepo.findAll(pageable);
        
        assertThat(states.isFirst(), equalTo(false));
        assertThat(states.isLast(), equalTo(false));
        assertThat(states.hasNext(), equalTo(true));
        assertThat(states.hasPrevious(), equalTo(true));
        assertThat(states.hasContent(), equalTo(true));

        assertThat(states.getTotalElements(), equalTo(50L));
        assertThat(states.getTotalPages(), equalTo(10));
        
        assertThat(states.getContent().toString(), equalTo("[Hawaii, Idaho, Illinois, Indiana, Iowa]"));
    }
    
    //------------------------------------------------- paging to list
    
    @Test
    public void testPagingToList() {
        int page = 4;
        int pageSize = 4;
        
        Pageable pageable = new PageRequest(page, pageSize);
        List<State> states = stateRepo.findByCapitalSinceGreaterThan(1800, pageable);
        
        assertThat(states.toString(), equalTo("[Maine, Michigan, Minnesota, Mississippi]"));
    }
    
    //------------------------------------------------- sorting all
    
    @Test
    public void testSortingAll() {

        Sort sort = new Sort(Direction.ASC, "abbreviation");
        Iterable<State> states = stateRepo.findAll(sort);
        
        StringBuffer abbreviations = new StringBuffer();
        for (State state : states) {
            abbreviations.append(state.getAbbreviation() + ",");
        }
        
        assertThat(abbreviations.toString(), equalTo("AK,AL,AR,AZ,CA,CO,CT,DE,FL,GA,HI,IA,ID,IL,IN,KS,KY,LA,MA," +
        		                                     "MD,ME,MI,MN,MO,MS,MT,NC,ND,NE,NH,NJ,NM,NV,NY,OH,OK,OR,PA," +
        		                                     "RI,SC,SD,TN,TX,UT,VA,VT,WA,WI,WV,WY,"));
    }
    
    //------------------------------------------------- sorting some
    
    @Test
    public void testSortingSome() {
        
        Sort sort = new Sort(Direction.DESC, "dateOfStatehood");
        Iterable<State> states = stateRepo.findByDateOfStatehoodGreaterThan(1900, sort);
        
        StringBuffer abbreviations = new StringBuffer();
        for (State state : states) {
            abbreviations.append(state.getDateOfStatehood() + ",");
        }
        
        assertThat(abbreviations.toString(), equalTo("1959,1959,1912,1912,1907,"));
    }
    
    //------------------------------------------------- paging and sorting
    
    @Test
    public void testPagingAndSorting() {
        int page = 3;
        int pageSize = 6;
        
        Pageable pageable = new PageRequest(page, pageSize, Direction.ASC, "capitalSince");
        Page<State> states = stateRepo.findAll(pageable);
        
        assertThat(states.getContent().toString(), equalTo("[Missouri, Tennessee, Maine, Illinois, Wisconsin, Texas]"));
    }

}