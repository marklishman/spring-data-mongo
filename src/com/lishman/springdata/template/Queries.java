package com.lishman.springdata.template;

import static org.springframework.data.mongodb.core.query.Criteria.*;
import static org.springframework.data.mongodb.core.query.Query.query;

import java.net.UnknownHostException;
import java.util.List;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;

import com.lishman.springdata.MongoTestData;
import com.lishman.springdata.domain.Continent;
import com.lishman.springdata.domain.Country;
import com.mongodb.MongoClient;

public class Queries {
    
    public static void main(String[] args) throws UnknownHostException {
        
        MongoClient client = new MongoClient("mongo-host");
        MongoOperations mongoOps = new MongoTemplate(client, "world");
        
        MongoTestData testData = new MongoTestData();
        testData.setMongoOperations(mongoOps);
        testData.countriesTestData();
        
        //------------------------------------------------- equality
        
        Continent asia = mongoOps.findOne(query(where("name").is("Asia")), Continent.class);
        
        System.out.println("Continent name is " + asia.getName());
        
        //------------------------------------------------- not equal to
        
        List<Continent> notAsia = mongoOps.find(query(where("name").ne("Asia")), Continent.class);
        
        System.out.println("Continent other than Asia " + notAsia);
        
        //------------------------------------------------- less than
        
        List<Country> smallCountries = mongoOps.find(query(where("area").lt(30000)), Country.class);
        
        System.out.println("Small countries " + smallCountries);
        
        //------------------------------------------------- between
        
        Criteria between = where("population").gt(5000000).lt(30000000);
        List<Country> populationBetween = mongoOps.find(query(between), Country.class);
        
        System.out.println("Medium populated countries " + populationBetween);
        
        //------------------------------------------------- in list
        
        List<Country> ghanaAndGambia = mongoOps.find(query(where("name").in("Ghana", "Gambia")), Country.class);
        List<Country> notGhanaAndGambia = mongoOps.find(query(where("name").nin("Ghana", "Gambia")), Country.class);
        
        System.out.printf("Ghana and Gambia %s and the others %s\n", ghanaAndGambia, notGhanaAndGambia);
        
        //------------------------------------------------- regular expression

        List<Country> regex = mongoOps.find(query(where("name").regex("G[ae].*")), Country.class);
        
        System.out.println("Starting with 'Ga' or 'Ge' " + regex);
        
        //------------------------------------------------- not
        
        List<Country> notRegex = mongoOps.find(query(where("name").not().regex("G[ae].*")), Country.class);
        
        System.out.println("Not starting with 'Ga' or 'Ge' " + notRegex);
        
        //------------------------------------------------- subdocument
        
        List<Country> asianCountries = mongoOps.find(query(where("continent.name").is("Asia")), Country.class);
        
        System.out.println("Asian countries " + asianCountries);
        
        //------------------------------------------------- and
        
        Criteria smallAreaAndBigPopulation = where("area").lt(500000).and("population").gt(30000000);
        List<Country> denselyPopulated = mongoOps.find(query(smallAreaAndBigPopulation), Country.class);
        
        System.out.println("Densely populated " + denselyPopulated);
        
        //------------------------------------------------- or
        
        Criteria smallArea = where("area").lt(50000);
        Criteria smallPopulation = where("population").lt(2000000);
        Criteria smallAreaOrPopulation = new Criteria().orOperator(smallArea, smallPopulation);
        List<Country> smallOrSparselyPopulated = mongoOps.find(query(smallAreaOrPopulation), Country.class);
        
        System.out.println("Small area or population  " + smallOrSparselyPopulated);
        
        //------------------------------------------------- and / or
        
        Criteria countries = where("name").regex("G[ae].*");
        Criteria andOr = new Criteria().andOperator(countries, smallAreaOrPopulation);
        List<Country> countryList = mongoOps.find(query(andOr), Country.class);
        
        System.out.println("Complex query  " + countryList);

        // Show the generated DBObject
        System.out.println(andOr.getCriteriaObject()); 
        
        /* 
               { "$and" : [ 
                            { "name" : { "$regex" : "G[ae].*"}} , 
                            { "$or" : [ 
                                        { "area" : { "$lt" : 50000}} , 
                                        { "population" : { "$lt" : 2000000}}
                                      ]
                            }
                          ]
               }
          
         */
    }
}

