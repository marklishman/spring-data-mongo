package com.lishman.springdata.repository;

// TODO custom interface (good one for tutorial with guide?)

/* This is my custom interface. 
 * 
 * It has nothing to do with Spring Data
 */

public interface MedalsRepositoryCustom {
    
    public int getBronzeCount(String countryName);

    public int getSilverCount(String countryName);
    
    public int getGoldCount(String countryName);

}