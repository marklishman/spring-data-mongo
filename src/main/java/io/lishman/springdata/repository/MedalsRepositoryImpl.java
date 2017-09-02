package io.lishman.springdata.repository;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;

import io.lishman.springdata.domain.OlympicMedals;
import io.lishman.springdata.domain.OlympicMedals.MedalType;

/* 
 * Spring will find this class because the name follows a convention of 
 * 
 *      <InterfaceName>Impl
 * 
 * An alternative would be to specify 
 * 
 *      @Repository("medalsRepositoryImpl")
 *      
 * then the class could be called anything.
 *  
 * However, in this case it would need to be picked up by a component scan.
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