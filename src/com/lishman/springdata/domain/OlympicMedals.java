package com.lishman.springdata.domain;

import java.util.HashMap;
import java.util.Map;

import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;



@Document(collection="medals")
@TypeAlias("medal")
public class OlympicMedals extends AbstractDocument {
    
    public enum MedalType {GOLD, SILVER, BRONZE};

    @Field("country")
    private String countryName;

    private Map<MedalType, Integer> medals = new HashMap<MedalType, Integer>();

    public OlympicMedals(String countryName, int gold, int silver, int bronze) {
        this.setCountryName(countryName);
        medals.put(MedalType.GOLD, gold);
        medals.put(MedalType.SILVER, silver);
        medals.put(MedalType.BRONZE, bronze);
    }
    
    // TODO persistence constructor - @PersistenceConstructor
    @PersistenceConstructor
    public OlympicMedals(String countryName, Map<MedalType, Integer> medals) {
        this.setCountryName(countryName);
        this.medals = medals;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getName() {
        return countryName;
    }
    
    public int getMedalCount(MedalType type) {
        return medals.get(type);
    }
    
}
