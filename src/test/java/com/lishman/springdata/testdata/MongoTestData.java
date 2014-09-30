package com.lishman.springdata.testdata;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.lishman.springdata.domain.City;
import com.lishman.springdata.domain.Continent;
import com.lishman.springdata.domain.Country;
import com.lishman.springdata.domain.Ocean;
import com.lishman.springdata.domain.OlympicMedals;
import com.lishman.springdata.domain.State;

@Component
public class MongoTestData {
    
    @Autowired private MongoOperations mongoOps;
    
    public void setMongoOperations(MongoOperations mongoOps) {
        this.mongoOps = mongoOps;
    }
    
    public void countriesTestData() {

        // ------------------------------------------------ Continents
        
        mongoOps.dropCollection(Continent.class);

        Continent africa         = new Continent(1, "Africa");
        Continent asia           = new Continent(2, "Asia");
        Continent europe         = new Continent(3, "Europe");
        Continent northAmerica   = new Continent(4, "North America");
        Continent southAmerica   = new Continent(5, "South America");
        Continent australia      = new Continent(6, "Australia");
        Continent antarctica     = new Continent(7, "Antarctica");
        
        mongoOps.insert(africa);
        mongoOps.insert(asia);
        mongoOps.insert(europe);
        mongoOps.insert(northAmerica);
        mongoOps.insert(southAmerica);
        mongoOps.insert(australia);
        mongoOps.insert(antarctica);
        // ------------------------------------------------ Continents
        
        mongoOps.dropCollection(Ocean.class);
        
        mongoOps.insert(new Ocean(1, "Artic", 5426000));
        mongoOps.insert(new Ocean(2, "Atlantic", 29630000));
        mongoOps.insert(new Ocean(3, "Indian", 26463000));
        mongoOps.insert(new Ocean(4, "Pacific", 60045000));
        mongoOps.insert(new Ocean(5, "Southern", 7846000));

        // ------------------------------------------------ Countries

        /* remove() instead of dropCollection() to preserve index
         * 
         * If dropCollection() were used, the index would be
         * created on startup and this would delete the collection
         * AND the index.
         */
        mongoOps.remove(new Query(), "countries");

        mongoOps.insert(new Country("Australia", 2966200, 21884000L, australia));
        mongoOps.insert(new Country("Gabon", 103347, 1475000L, africa));
        mongoOps.insert(new Country("Gambia", 4361, 1705000L, africa));
        mongoOps.insert(new Country("Georgia", 26900, 4382100L, europe));
        Country germany = new Country("Germany", 137847, 82046000L, europe);
        mongoOps.insert(germany);
        mongoOps.insert(new Country("Ghana", 92098, 23837000L, africa));
        mongoOps.insert(new Country("Greece", 50949, 11257285L, europe));
        Country japan = new Country("Japan", 145925, 126659683L, asia);
        mongoOps.insert(japan);
        mongoOps.insert(new Country("New Zealand", 104454, 4320300L, australia));
        mongoOps.insert(new Country("Serbia", 34116, 7120666L, europe));
        Country usa = new Country("USA", 3794101, 316637000L, northAmerica);
        mongoOps.insert(usa);
        mongoOps.insert(new Country("Vietnam", 128565, 90388000L, asia));
        
        // ------------------------------------------------ Cities

        mongoOps.dropCollection(City.class);

        mongoOps.insert(new City(new BigInteger("1"), "Tokyo", japan, Arrays.asList(new String[]{"Mount Fuji", "Tokyo Skytree", "Disneyland"})));
        mongoOps.insert(new City(new BigInteger("2"), "Munich", germany, Arrays.asList(new String[]{"English Garden", "BMW Museum"})));
        mongoOps.insert(new City(new BigInteger("3"), "Berlin", germany, Arrays.asList(new String[]{"Brandenburg Gate", "Berlin TV Tower", "Reichstag building"})));
        mongoOps.insert(new City(new BigInteger("4"), "New York", usa, Arrays.asList(new String[]{"Empire State Building", "Statue of Liberty", "Central Park", "Times Square", "Bronx Zoo"})));
        mongoOps.insert(new City(new BigInteger("5"), "Boston", usa, Arrays.asList(new String[]{"Freedom Trail", "Fenway Park", "Beacon Hill"})));

        // ------------------------------------------------ Olympic Medals

        mongoOps.dropCollection(OlympicMedals.class);
        
        List<OlympicMedals> medals = new ArrayList<OlympicMedals>();

        medals.add(new OlympicMedals("United States",    976, 758, 666));
        medals.add(new OlympicMedals("Soviet Union",     395, 319, 296));
        medals.add(new OlympicMedals("Great Britain",    236, 272, 272));
        medals.add(new OlympicMedals("France",           202, 223, 246));
        medals.add(new OlympicMedals("Germany",          174, 182, 217));
        medals.add(new OlympicMedals("Italy",            198, 166, 185));
        medals.add(new OlympicMedals("Sweden",           143, 164, 176));
        medals.add(new OlympicMedals("East Germany",     153, 129, 127));
        medals.add(new OlympicMedals("China",            201, 144, 128));
        medals.add(new OlympicMedals("Russia",           133, 122, 142));
        medals.add(new OlympicMedals("Hungary",          167, 144, 165));
        medals.add(new OlympicMedals("Australia",        138, 153, 177));
        
        mongoOps.insertAll(medals);
        
        // ------------------------------------------------ States
        
        mongoOps.dropCollection(State.class);
        
        List<State> states = new ArrayList<State>();
        states.add(new State("Alabama", "AL", 1819, "Montgomery", 1846));
        states.add(new State("Alaska", "AK", 1959, "Juneau", 1906));
        states.add(new State("Arizona", "AZ", 1912, "Phoenix", 1889));
        states.add(new State("Arkansas", "AR", 1836, "Little Rock", 1821));
        states.add(new State("California", "CA", 1850, "Sacramento", 1854));
        states.add(new State("Colorado", "CO", 1876, "Denver", 1867));
        states.add(new State("Connecticut", "CT", 1788, "Hartford", 1875));
        states.add(new State("Delaware", "DE", 1787, "Dover", 1777));
        states.add(new State("Florida", "FL", 1845, "Tallahassee", 1824));
        states.add(new State("Georgia", "GA", 1788, "Atlanta", 1868));
        states.add(new State("Hawaii", "HI", 1959, "Honolulu", 1845));
        states.add(new State("Idaho", "ID", 1890, "Boise", 1865));
        states.add(new State("Illinois", "IL", 1818, "Springfield", 1837));
        states.add(new State("Indiana", "IN", 1816, "Indianapolis", 1825));
        states.add(new State("Iowa", "IA", 1846, "Des Moines", 1857));
        states.add(new State("Kansas", "KS", 1861, "Topeka", 1856));
        states.add(new State("Kentucky", "KY", 1792, "Frankfort", 1792));
        states.add(new State("Louisiana", "LA", 1812, "Baton Rouge", 1880));
        states.add(new State("Maine", "ME", 1820, "Augusta", 1832));
        states.add(new State("Maryland", "MD", 1788, "Annapolis", 1694));
        states.add(new State("Massachusetts", "MA", 1788, "Boston", 1630));
        states.add(new State("Michigan", "MI", 1837, "Lansing", 1847));
        states.add(new State("Minnesota", "MN", 1858, "Saint Paul", 1849));
        states.add(new State("Mississippi", "MS", 1817, "Jackson", 1821));
        states.add(new State("Missouri", "MO", 1821, "Jefferson City", 1826));
        states.add(new State("Montana", "MT", 1889, "Helena", 1875));
        states.add(new State("Nebraska", "NE", 1867, "Lincoln", 1867));
        states.add(new State("Nevada", "NV", 1864, "Carson City", 1861));
        states.add(new State("New Hampshire", "NH", 1788, "Concord", 1808));
        states.add(new State("New Jersey", "NJ", 1787, "Trenton", 1784));
        states.add(new State("New Mexico", "NM", 1912, "Santa Fe", 1610));
        states.add(new State("New York", "NY", 1788, "Albany", 1797));
        states.add(new State("North Carolina", "NC", 1789, "Raleigh", 1792));
        states.add(new State("North Dakota", "ND", 1889, "Bismarck", 1883));
        states.add(new State("Ohio", "OH", 1803, "Columbus", 1816));
        states.add(new State("Oklahoma", "OK", 1907, "Oklahoma City", 1910));
        states.add(new State("Oregon", "OR", 1859, "Salem", 1855));
        states.add(new State("Pennsylvania", "PA", 1787, "Harrisburg", 1812));
        states.add(new State("Rhode Island", "RI", 1790, "Providence", 1900));
        states.add(new State("South Carolina", "SC", 1788, "Columbia", 1786));
        states.add(new State("South Dakota", "SD", 1889, "Pierre", 1889));
        states.add(new State("Tennessee", "TN", 1796, "Nashville", 1826));
        states.add(new State("Texas", "TX", 1845, "Austin", 1839));
        states.add(new State("Utah", "UT", 1896, "Salt Lake City", 1858));
        states.add(new State("Vermont", "VT", 1791, "Montpelier", 1805));
        states.add(new State("Virginia", "VA", 1788, "Richmond", 1780));
        states.add(new State("Washington", "WA", 1889, "Olympia", 1853));
        states.add(new State("West Virginia", "WV", 1863, "Charleston", 1885));
        states.add(new State("Wisconsin", "WI", 1848, "Madison", 1838));
        states.add(new State("Wyoming", "WY", 1890, "Cheyenne", 1869));
        
        mongoOps.insertAll(states);
    }

}