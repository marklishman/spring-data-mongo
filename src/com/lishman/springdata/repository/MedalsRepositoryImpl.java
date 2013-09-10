package com.lishman.springdata.repository;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;

import com.lishman.springdata.domain.OlympicMedals;
import com.lishman.springdata.domain.OlympicMedals.MedalType;

/* The custom interface is implemented here.
 * 
 * The name follows a convention of <InterfaceName>Impl
 * 
 * Spring searches for this name during a component scan.
 * An alternative would be to specify 
 * 
 *      @Repository("medalsRepositoryImpl")
 *      
 * then the class could be called anything.
 *  
 * Either way it will only be picked up by a component scan.
 * 
 *      @ComponentScan(basePackageClasses=RepositoryPackage.class)
 */
public class MedalsRepositoryImpl implements MedalsRepositoryCustom {
    
    @Autowired private MongoOperations operations;

    @Override
    public int getBronzeCount(String countryName) {
        return getMedalsForCountry(countryName).getMedalCount(MedalType.BRONZE);
    }

    @Override
    public int getSilverCount(String countryName) {
        return getMedalsForCountry(countryName).getMedalCount(MedalType.SILVER);
    }

    @Override
    public int getGoldCount(String countryName) {
        return getMedalsForCountry(countryName).getMedalCount(MedalType.GOLD);
    }

    private OlympicMedals getMedalsForCountry(String countryName) {
        return operations.findOne(query(where("countryName").is(countryName)), OlympicMedals.class);
    }


}
