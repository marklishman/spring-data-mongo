package com.lishman.springdata.standalone;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;
import static org.springframework.data.mongodb.core.query.Update.update;

import java.net.UnknownHostException;
import java.util.List;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.lishman.springdata.domain.Continent;
import com.lishman.springdata.domain.Country;
import com.lishman.springdata.domain.OlympicMedals;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;

public class Crud {
    
    public static void main(String[] args) throws UnknownHostException {
        
        MongoClient client = new MongoClient("mongo-host");
        MongoOperations mongoOps = new MongoTemplate(client, "world");
        
        
        //------------------------------------------------- insert
        
        OlympicMedals finland = new OlympicMedals("Finland", 101, 84, 117);
        mongoOps.insert(finland);

        
        //------------------------------------------------- read
        
        List<Continent> continents = mongoOps.findAll(Continent.class);
        
        for(Continent continent : continents) {
            System.out.println(continent.getName());
        }

        
        //------------------------------------------------- update
        
        mongoOps.updateFirst(query(where("name").is("Serbia")), update("population", 7120777L), Country.class);

        
        //------------------------------------------------- delete
        
        mongoOps.remove(query(where("_id").is("3")), Continent.class);

        
        displayDocumentsFromCursor(mongoOps.getCollection("continents"));

    }
    
    //------------------------------------------------- display results

    private static void displayDocumentsFromCursor(final DBCollection collection) {
        DBCursor cursor = collection.find();
        try {
            while(cursor.hasNext()) {
                System.out.println(cursor.next());
            }
            
        } catch (Exception e) {
            cursor.close();
        }
    }

}
