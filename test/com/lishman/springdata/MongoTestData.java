package com.lishman.springdata;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Component;

import com.lishman.springdata.domain.City;
import com.lishman.springdata.domain.Continent;
import com.lishman.springdata.domain.Country;
import com.lishman.springdata.domain.OlympicMedals;

@Component
public class MongoTestData {
    
    @Autowired private MongoOperations mongoOps;
    
    public void setMongoOperations(MongoOperations mongoOps) {
        this.mongoOps = mongoOps;
    }
    
    public void countriesTestData() {
        
        mongoOps.dropCollection(Continent.class);
        mongoOps.dropCollection(Country.class);
        mongoOps.dropCollection(City.class);
        mongoOps.dropCollection(OlympicMedals.class);

        // ------------------------------------------------ Continents
        
        Continent africa         = new Continent("1", "Africa");
        Continent asia           = new Continent("2", "Asia");
        Continent europe         = new Continent("3", "Europe");
        Continent northAmerica   = new Continent("4", "North America");
        Continent southAmerica   = new Continent("5", "South America");
        Continent australia      = new Continent("6", "Australia");
        Continent antarctica     = new Continent("7", "Antarctica");
        
        mongoOps.insert(africa);
        mongoOps.insert(asia);
        mongoOps.insert(europe);
        mongoOps.insert(northAmerica);
        mongoOps.insert(southAmerica);
        mongoOps.insert(australia);
        mongoOps.insert(antarctica);

        // ------------------------------------------------ Countries

        mongoOps.insert(new Country("Australia", 2966200, 21884000L, australia));
        mongoOps.insert(new Country("Gabon", 103347, 1475000L, africa));
        mongoOps.insert(new Country("Gambia", 4361, 1705000L, africa));
        mongoOps.insert(new Country("Georgia", 26900, 4382100L, europe));
        Country germany = new Country("Germany", 137847, 82046000L, europe);
        mongoOps.insert(germany);
        mongoOps.insert(new Country("Ghana", 92098, 23837000L, australia));
        mongoOps.insert(new Country("Greece", 50949, 11257285L, europe));
        Country japan = new Country("Japan", 145925, 126659683L, asia);
        mongoOps.insert(japan);
        mongoOps.insert(new Country("New Zealand", 104454, 4320300L, australia));
        mongoOps.insert(new Country("Serbia", 34116, 7120666L, europe));
        mongoOps.insert(new Country("Vietnam", 128565, 90388000L, asia));
        
        // ------------------------------------------------ Cities

        mongoOps.insert(new City("1", "Tokyo", japan, Arrays.asList(new String[]{"Mount Fuji", "Tokyo Skytree", "Disneyland"})));
        mongoOps.insert(new City("2", "Munich", germany, Arrays.asList(new String[]{"English Garden", "BMW Museum"})));
        mongoOps.insert(new City("3", "Berlin", germany, Arrays.asList(new String[]{"Brandenburg Gate", "Berlin TV Tower", "Reichstag building"})));

        // ------------------------------------------------ Olympic Medals
        
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
        
    }

}
