package com.lishman.springdata.domain;

import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="states")
@TypeAlias("state")
public class State extends AbstractDocument  {
    
    private String name;
    
    private String abbreviation;
    
    private int dateOfStatehood;
    
    private String capital;
    
    private int capitalSince;
    
    public State(String name, 
                 String abbreviation, 
                 int dateOfStatehood,
                 String capital, 
                 int capitalSince) {
        this.setName(name);
        this.setAbbreviation(abbreviation);
        this.setDateOfStatehood(dateOfStatehood);
        this.setCapital(capital);
        this.setCapitalSince(capitalSince);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public int getDateOfStatehood() {
        return dateOfStatehood;
    }

    public void setDateOfStatehood(int dateOfStatehood) {
        this.dateOfStatehood = dateOfStatehood;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public int getCapitalSince() {
        return capitalSince;
    }

    public void setCapitalSince(int capitalSince) {
        this.capitalSince = capitalSince;
    }
    
    
    public String toString() {
        return this.getName();
    }

}
