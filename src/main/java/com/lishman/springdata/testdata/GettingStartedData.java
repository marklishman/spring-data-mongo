package com.lishman.springdata.testdata;

import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.util.JSON;

public class GettingStartedData {

    public static void continentsTestData() {

        MongoClient client = mongoClient();
        DB db = client.getDB( "world" );

        DBCollection continents = db.getCollection("continent");
        continents.drop();
        
        continents.insert((DBObject) JSON.parse("{_id : 1, name : 'Africa'}"));
        continents.insert((DBObject) JSON.parse("{_id : 2, name : 'Asia'}"));
        continents.insert((DBObject) JSON.parse("{_id : 3, name : 'Europe'}"));
        continents.insert((DBObject) JSON.parse("{_id : 4, name : 'North America'}"));
        continents.insert((DBObject) JSON.parse("{_id : 5, name : 'South America'}"));
        continents.insert((DBObject) JSON.parse("{_id : 6, name : 'Australia'}"));
        continents.insert((DBObject) JSON.parse("{_id : 7, name : 'Antarctica'}"));
        
    }

    private static MongoClient mongoClient() {
        try {
            return new MongoClient( "mongo-host" , 27017 );
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }
}
