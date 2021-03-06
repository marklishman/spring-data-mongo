package io.lishman.springdata.repository;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import io.lishman.springdata.TestData;
import io.lishman.springdata.config.MongoConfig;
import io.lishman.springdata.domain.Country;

@ContextConfiguration(classes={MongoConfig.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class CountryRepositoryTest {

    @Autowired private CountryRepository countryRepo;
    @Autowired private MongoOperations mongoOps;
    
    @Before
    public void reset() {
        TestData.countries();
    }

    //------------------------------------------------- equality
    
    @Test
    public void testFindByName() {
        Country japan = countryRepo.findByName("Japan");
        assertThat(japan.getName(), is(equalTo("Japan")));
    }

    @Test
    public void testFindByNameQuery() {
        Country japan = countryRepo.findByNameQuery("Japan");
        assertThat(japan.getName(), is(equalTo("Japan")));
    }

    //------------------------------------------------- not equal

    @Test
    public void testFindByNameNotEqual() {
        List<Country> notJapan = countryRepo.findByNameNot("Japan");
        assertThat(notJapan.toString(), is(equalTo("[Australia, Gabon, Gambia, Georgia, Germany, Ghana, Greece, New Zealand, Serbia, USA, Vietnam]")));
    }
    
    @Test
    public void testFindByNameNotEqualQuery() {
        List<Country> notJapan = countryRepo.findByNameNotQuery("Japan");
        assertThat(notJapan.toString(), is(equalTo("[Australia, Gabon, Gambia, Georgia, Germany, Ghana, Greece, New Zealand, Serbia, USA, Vietnam]")));
    }

    //------------------------------------------------- like / regex
    
    @Test
    public void testFindByNameLike() {
        List<Country> containingLand = countryRepo.findByNameLike("land");
        assertThat(containingLand.toString(), is(equalTo("[New Zealand]")));
    }
    
    @Test
    public void testReadByNameRegex() {
        List<Country> startsWithGaOrGe = countryRepo.readByNameRegex("^G[ae].*");
        assertThat(startsWithGaOrGe.toString(), is(equalTo("[Gabon, Gambia, Georgia, Germany]")));
    }
    
    @Test
    public void testGetByNameLikeQuery() {
        List<Country> containsBia = countryRepo.getByNameRegexQuery("bia");
        assertThat(containsBia.toString(), is(equalTo("[Gambia, Serbia]")));
    }
    
    //------------------------------------------------- nested
    
    @Test
    public void testFindByContinentName() {
        List<Country> asianCountries = countryRepo.findByContinentName("Asia");
        assertThat(asianCountries.toString(), is(equalTo("[Japan, Vietnam]")));
    }
    
    @Test
    public void testFindByContinentNameQuery() {
        List<Country> africanCountries = countryRepo.findByContinentName("Africa");
        assertThat(africanCountries.toString(), is(equalTo("[Gabon, Gambia, Ghana]")));
    }
    
    //------------------------------------------------- null / not null
    
    @Test
    public void testFindByPopulationIsNull() {
        Query query = query(where("name").is("Gabon"));
        Update update = new Update().unset("population");
        mongoOps.updateFirst(query, update, Country.class);
        
        List<Country> nullPopulation = countryRepo.findByPopulationIsNull();
        assertThat(nullPopulation.toString(), is(equalTo("[Gabon]")));

        List<Country> notNullPopulation = countryRepo.findByPopulationIsNotNull();
        assertThat(notNullPopulation.toString(), is(equalTo("[Australia, Gambia, Georgia, Germany, Ghana, Greece, Japan, New Zealand, Serbia, USA, Vietnam]")));
    }
    
    @Test
    public void testFindByPopulationIsNullQuery() {
        Query query = query(where("continent.name").is("Africa"));
        Update update = new Update().unset("population");
        mongoOps.updateMulti(query, update, Country.class);
        
        List<Country> nullPopulation = countryRepo.findByPopulationIsNullQuery();
        assertThat(nullPopulation.toString(), is(equalTo("[Gabon, Gambia, Ghana]")));
        
        List<Country> notNullPopulation = countryRepo.findByPopulationIsNotNullQuery();
        assertThat(notNullPopulation.toString(), is(equalTo("[Australia, Georgia, Germany, Greece, Japan, New Zealand, Serbia, USA, Vietnam]")));
    }
    
    //------------------------------------------------- less than / greater than
    
    @Test
    public void testFindByAreaInSquareMilesLessThan() {
        List<Country> smallCountries = countryRepo.findByAreaInSquareMilesLessThan(30000);
        assertThat(smallCountries .toString(), is(equalTo("[Gambia, Georgia]")));
    }

    @Test
    public void testFindByAreaInSquareMilesLessThanQuery() {
        List<Country> smallCountries = countryRepo.findByAreaInSquareMilesLessThanQuery(40000);
        assertThat(smallCountries.toString(), is(equalTo("[Gambia, Georgia, Serbia]")));
    }
    
    @Test
    public void testFindByPopulationGreaterThan() {
        List<Country> largePopulation = countryRepo.findByPopulationGreaterThan(22000000);
        assertThat(largePopulation.toString(), is(equalTo("[Germany, Ghana, Japan, USA, Vietnam]")));
    }
    
    @Test
    public void testFindByPopulationGreaterThanQuery() {
        List<Country> largePopulation = countryRepo.findByPopulationGreaterThanQuery(90000000);
        assertThat(largePopulation.toString(), is(equalTo("[Japan, USA, Vietnam]")));
    }
    
    //------------------------------------------------- between

    @Test
    public void testFindByPopulationBetween() {
        List<Country> largePopulation = countryRepo.findByPopulationBetween(20000000, 50000000);
        assertThat(largePopulation.toString(), is(equalTo("[Australia, Ghana]")));
    }
    
    @Test
    public void testFindByPopulationBetweenQuery() {
        List<Country> largePopulation = countryRepo.findByPopulationBetweenQuery(10000000, 30000000);
        assertThat(largePopulation.toString(), is(equalTo("[Australia, Ghana, Greece]")));
    }

    //------------------------------------------------- and

    @Test
    public void testFindByContinentNameAndPopulationLessThan() {
        List<Country> smallPopEuropean = countryRepo.findByContinentNameAndPopulationLessThan("Europe", 10000000);
        assertThat(smallPopEuropean.toString(), is(equalTo("[Georgia, Serbia]")));
    }
    
    @Test
    public void testFindByContinentNameAndPopulationLessThanQuery() {
        List<Country> smallPopAfrican = countryRepo.findByContinentNameAndPopulationLessThanQuery("Africa", 10000000);
        assertThat(smallPopAfrican.toString(), is(equalTo("[Gabon, Gambia]")));
    }
    
    //------------------------------------------------- or

    @Test
    public void testFindByPopulationLessThanOrAreaInSquareMilesLessThan() {
        List<Country> smallPopOrArea = countryRepo.findByPopulationLessThanOrAreaInSquareMilesLessThan(4000000, 80000);
        assertThat(smallPopOrArea.toString(), is(equalTo("[Gabon, Gambia, Georgia, Greece, Serbia]")));
    }
    
    @Test
    public void testFindByPopulationLessThanOrAreaInSquareMilesLessThanQuery() {
        List<Country> smallPopOrArea = countryRepo.findByPopulationLessThanOrAreaInSquareMilesLessThanQuery(1000000, 70000);
        assertThat(smallPopOrArea.toString(), is(equalTo("[Gambia, Georgia, Greece, Serbia]")));
    }
    
    //------------------------------------------------- orderBy
    
    @Test
    public void testFindByContinentNameOrderByPopulation() {
        List<Country> europeanCountries = countryRepo.findByContinentNameOrderByPopulationDesc("Europe");
        assertThat(europeanCountries.toString(), is(equalTo("[Germany, Greece, Serbia, Georgia]")));
    }
     
    //------------------------------------------------- fields
    
    @Test
    public void testFindByContinentNameWithFields() {
        List<Country> asianCountries = countryRepo.findByContinentNameJustReturnNameQuery("Asia");
        assertThat(asianCountries.toString(), is(equalTo("[Japan, Vietnam]")));
        assertThat(asianCountries.get(0).getId(), is(equalTo(null)));
        assertThat(asianCountries.get(0).getPopulation(), is(equalTo(null)));
        assertThat(asianCountries.get(0).getAreaInSquareMiles(), is(equalTo(null)));
    }
}
