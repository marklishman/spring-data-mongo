package io.lishman.springdata.domain;

import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection="cities")
@TypeAlias("city")
public class City extends AbstractDocument {

    private String name;
    private List<String> attractions;
   
    @DBRef
    private Country country;

    public City(String name, final Country country, List<String> attractions) {
        this.setName(name);
        this.setCountry(country);
        this.setAttractions(attractions);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    
    public void setAttractions(List<String> attractions) {
        this.attractions = attractions;
    }
    
    public List<String> getAttractions() {
        return attractions;
    }
    
    public void setCountry(Country country) {
        this.country = country;
    }
    
    public Country getCountry() {
        return country;
    }
    
    public String toString() {
        return getName();
    }
}
