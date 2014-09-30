package com.lishman.springdata.testdata;

import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.util.JSON;

public class TestData {
    
    public static void main(String[] args) {
        TestData.all();
    }
    
    public static void all() {
        TestData.continents();
        TestData.oceans();
        TestData.countries();
        TestData.cities();
        TestData.medals();
        TestData.states();
    }

    public static void continents() {

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

    public static void oceans() {
        
        MongoClient client = mongoClient();
        DB db = client.getDB( "world" );
        
        DBCollection oceans = db.getCollection("oceans");
        oceans.drop();
        
        oceans.insert((DBObject) JSON.parse("{_id : 1, name : 'Artic', area: 5426000}"));
        oceans.insert((DBObject) JSON.parse("{_id : 2, name : 'Atlantic', area: 29630000}"));
        oceans.insert((DBObject) JSON.parse("{_id : 3, name : 'Indian', area: 26463000}"));
        oceans.insert((DBObject) JSON.parse("{_id : 4, name : 'Pacific', area: 60045000}"));
        oceans.insert((DBObject) JSON.parse("{_id : 5, name : 'Southern', area: 7846000}"));
    }
    
    public static void countries() {
        
        MongoClient client = mongoClient();
        DB db = client.getDB( "world" );
        
        // TODO Remove rather than drop
        /* remove() instead of dropCollection() to preserve index
         * 
         * If dropCollection() were used, the index would be
         * created on startup and this would delete the collection
         * AND the index.
         */

        DBCollection countries = db.getCollection("countries");
        countries.drop();
        
        countries.insert((DBObject) JSON.parse("{ '_id' : '542969d3f21595edd9587042', '_class' : 'ctry', 'name' : 'Australia', 'area' : 2966200, 'pop' : 21884000, 'continent' : { '_id' : 6, 'name' : 'Australia' } }"));
        countries.insert((DBObject) JSON.parse("{ '_id' : '542969d3f21595edd9587043', '_class' : 'ctry', 'name' : 'Gabon', 'area' : 103347, 'pop' : 1475000, 'continent' : { '_id' : 1, 'name' : 'Africa' } }"));
        countries.insert((DBObject) JSON.parse("{ '_id' : '542969d3f21595edd9587044', '_class' : 'ctry', 'name' : 'Gambia', 'area' : 4361, 'pop' : 1705000, 'continent' : { '_id' : 1, 'name' : 'Africa' } }"));
        countries.insert((DBObject) JSON.parse("{ '_id' : '542969d3f21595edd9587045', '_class' : 'ctry', 'name' : 'Georgia', 'area' : 26900, 'pop' : 4382100, 'continent' : { '_id' : 3, 'name' : 'Europe' } }"));
        countries.insert((DBObject) JSON.parse("{ '_id' : '542969d3f21595edd9587046', '_class' : 'ctry', 'name' : 'Germany', 'area' : 137847, 'pop' : 82046000, 'continent' : { '_id' : 3, 'name' : 'Europe' } }"));
        countries.insert((DBObject) JSON.parse("{ '_id' : '542969d3f21595edd9587047', '_class' : 'ctry', 'name' : 'Ghana', 'area' : 92098, 'pop' : 23837000, 'continent' : { '_id' : 1, 'name' : 'Africa' } }"));
        countries.insert((DBObject) JSON.parse("{ '_id' : '542969d3f21595edd9587048', '_class' : 'ctry', 'name' : 'Greece', 'area' : 50949, 'pop' : 11257285, 'continent' : { '_id' : 3, 'name' : 'Europe' } }"));
        countries.insert((DBObject) JSON.parse("{ '_id' : '542969d3f21595edd9587049', '_class' : 'ctry', 'name' : 'Japan', 'area' : 145925, 'pop' : 126659683, 'continent' : { '_id' : 2, 'name' : 'Asia' } }"));
        countries.insert((DBObject) JSON.parse("{ '_id' : '542969d3f21595edd958704a', '_class' : 'ctry', 'name' : 'New Zealand', 'area' : 104454, 'pop' : 4320300, 'continent' : { '_id' : 6, 'name' : 'Australia' } }"));
        countries.insert((DBObject) JSON.parse("{ '_id' : '542969d3f21595edd958704b', '_class' : 'ctry', 'name' : 'Serbia', 'area' : 34116, 'pop' : 7120666, 'continent' : { '_id' : 3, 'name' : 'Europe' } }"));
        countries.insert((DBObject) JSON.parse("{ '_id' : '542969d3f21595edd958704c', '_class' : 'ctry', 'name' : 'USA', 'area' : 3794101, 'pop' : 316637000, 'continent' : { '_id' : 4, 'name' : 'North America' } }"));
        countries.insert((DBObject) JSON.parse("{ '_id' : '542969d3f21595edd958704d', '_class' : 'ctry', 'name' : 'Vietnam', 'area' : 128565, 'pop' : 90388000, 'continent' : { '_id' : 2, 'name' : 'Asia' } }"));
        countries.insert((DBObject) JSON.parse("{ '_id' : '542969d3f21595edd958708c', '_class' : 'ctry', 'name' : 'Iceland', 'area' : 39770, 'pop' : 321857, 'continent' : { '_id' : 3, 'name' : 'Europe' } }"));        
    }
    
    public static void cities() {
        
        MongoClient client = mongoClient();
        DB db = client.getDB( "world" );
        
        DBCollection cities = db.getCollection("cities");
        cities.drop();
    
        // TODO references
//        cities.insert((DBObject) JSON.parse("{ '_id' : '1', '_class' : 'city', 'name' : 'Tokyo', 'attractions' : [ 'Mount Fuji', 'Tokyo Skytree', 'Disneyland' ], 'country' : 'countries', ObjectId('542969d3f21595edd9587049') }"));
//        cities.insert((DBObject) JSON.parse("{ '_id' : '2', '_class' : 'city', 'name' : 'Munich', 'attractions' : [ 'English Garden', 'BMW Museum' ], 'country' : 'countries', ObjectId('542969d3f21595edd9587046') }"));
//        cities.insert((DBObject) JSON.parse("{ '_id' : '3', '_class' : 'city', 'name' : 'Berlin', 'attractions' : [ 'Brandenburg Gate', 'Berlin TV Tower', 'Reichstag building' ], 'country' : 'countries', ObjectId('542969d3f21595edd9587046') }"));
//        cities.insert((DBObject) JSON.parse("{ '_id' : '4', '_class' : 'city', 'name' : 'New York', 'attractions' : [ 'Empire State Building', 'Statue of Liberty', 'Central Park', 'Times Square', 'Bronx Zoo' ], 'country' : 'countries', ObjectId('542969d3f21595edd958704c') }"));
//        cities.insert((DBObject) JSON.parse("{ '_id' : '5', '_class' : 'city', 'name' : 'Boston', 'attractions' : [ 'Freedom Trail', 'Fenway Park', 'Beacon Hill' ],'country' : 'countries', ObjectId('542969d3f21595edd958704c') }"));
    }
    
    public static void medals() {
        
        MongoClient client = mongoClient();
        DB db = client.getDB( "world" );
        
        DBCollection medals = db.getCollection("medals");
        medals.drop();
        
        medals.insert((DBObject) JSON.parse("{ '_id' : '542969d3f21595edd958704e', '_class' : 'medal', 'name' : 'United States', 'medals' : [ 666, 758, 976 ] }"));
        medals.insert((DBObject) JSON.parse("{ '_id' : '542969d3f21595edd958704f', '_class' : 'medal', 'name' : 'Soviet Union', 'medals' : [ 296, 319, 395] }"));
        medals.insert((DBObject) JSON.parse("{ '_id' : '542969d3f21595edd9587050', '_class' : 'medal', 'name' : 'Great Britain', 'medals' : [ 272, 272, 236 ] }"));
        medals.insert((DBObject) JSON.parse("{ '_id' : '542969d3f21595edd9587051', '_class' : 'medal', 'name' : 'France', 'medals' : [ 246, 223, 202 ] }"));
        medals.insert((DBObject) JSON.parse("{ '_id' : '542969d3f21595edd9587052', '_class' : 'medal', 'name' : 'Germany', 'medals' : [ 217, 182, 174 ] }"));
        medals.insert((DBObject) JSON.parse("{ '_id' : '542969d3f21595edd9587053', '_class' : 'medal', 'name' : 'Italy', 'medals' : [ 185, 166, 198 ] }"));
        medals.insert((DBObject) JSON.parse("{ '_id' : '542969d3f21595edd9587054', '_class' : 'medal', 'name' : 'Sweden', 'medals' : [ 176, 164, 143 ] }"));
        medals.insert((DBObject) JSON.parse("{ '_id' : '542969d3f21595edd9587055', '_class' : 'medal', 'name' : 'East Germany', 'medals' : [ 127, 129, 153] }"));
        medals.insert((DBObject) JSON.parse("{ '_id' : '542969d3f21595edd9587056', '_class' : 'medal', 'name' : 'China', 'medals' : [ 128, 144, 201 ] }"));
        medals.insert((DBObject) JSON.parse("{ '_id' : '542969d3f21595edd9587057', '_class' : 'medal', 'name' : 'Russia', 'medals' : [ 142, 122, 133 ] }"));
        medals.insert((DBObject) JSON.parse("{ '_id' : '542969d3f21595edd9587058', '_class' : 'medal', 'name' : 'Hungary', 'medals' : [ 165, 144, 167 ] }"));
        medals.insert((DBObject) JSON.parse("{ '_id' : '542969d3f21595edd9587059', '_class' : 'medal', 'name' : 'Australia', 'medals' : [ 177, 153, 138 ] }"));
    }
    
    public static void states() {
        
        MongoClient client = mongoClient();
        DB db = client.getDB( "world" );
        
        DBCollection states = db.getCollection("states");
        states.drop();
        
        states.insert((DBObject) JSON.parse("{ '_id' : '542969d3f21595edd958705a', '_class' : 'state', 'name' : 'Alabama', 'abbreviation' : 'AL', 'dateOfStatehood' : 1819, 'capital' : 'Montgomery', 'capitalSince' : 1846 }"));
        states.insert((DBObject) JSON.parse("{ '_id' : '542969d3f21595edd958705b', '_class' : 'state', 'name' : 'Alaska', 'abbreviation' : 'AK', 'dateOfStatehood' : 1959, 'capital' : 'Juneau', 'capitalSince' : 1906 }"));
        states.insert((DBObject) JSON.parse("{ '_id' : '542969d3f21595edd958705c', '_class' : 'state', 'name' : 'Arizona', 'abbreviation' : 'AZ', 'dateOfStatehood' : 1912, 'capital' : 'Phoenix', 'capitalSince' : 1889 }"));
        states.insert((DBObject) JSON.parse("{ '_id' : '542969d3f21595edd958705d', '_class' : 'state', 'name' : 'Arkansas', 'abbreviation' : 'AR', 'dateOfStatehood' : 1836, 'capital' : 'Little Rock', 'capitalSince' : 1821 }"));
        states.insert((DBObject) JSON.parse("{ '_id' : '542969d3f21595edd958705e', '_class' : 'state', 'name' : 'California', 'abbreviation' : 'CA', 'dateOfStatehood' : 1850, 'capital' : 'Sacramento', 'capitalSince' : 1854 }"));
        states.insert((DBObject) JSON.parse("{ '_id' : '542969d3f21595edd958705f', '_class' : 'state', 'name' : 'Colorado', 'abbreviation' : 'CO', 'dateOfStatehood' : 1876, 'capital' : 'Denver', 'capitalSince' : 1867 }"));
        states.insert((DBObject) JSON.parse("{ '_id' : '542969d3f21595edd9587060', '_class' : 'state', 'name' : 'Connecticut', 'abbreviation' : 'CT', 'dateOfStatehood' : 1788, 'capital' : 'Hartford', 'capitalSince' : 1875 }"));
        states.insert((DBObject) JSON.parse("{ '_id' : '542969d3f21595edd9587061', '_class' : 'state', 'name' : 'Delaware', 'abbreviation' : 'DE', 'dateOfStatehood' : 1787, 'capital' : 'Dover', 'capitalSince' : 1777 }"));
        states.insert((DBObject) JSON.parse("{ '_id' : '542969d3f21595edd9587062', '_class' : 'state', 'name' : 'Florida', 'abbreviation' : 'FL', 'dateOfStatehood' : 1845, 'capital' : 'Tallahassee', 'capitalSince' : 1824 }"));
        states.insert((DBObject) JSON.parse("{ '_id' : '542969d3f21595edd9587063', '_class' : 'state', 'name' : 'Georgia', 'abbreviation' : 'GA', 'dateOfStatehood' : 1788, 'capital' : 'Atlanta', 'capitalSince' : 1868 }"));
        states.insert((DBObject) JSON.parse("{ '_id' : '542969d3f21595edd9587064', '_class' : 'state', 'name' : 'Hawaii', 'abbreviation' : 'HI', 'dateOfStatehood' : 1959, 'capital' : 'Honolulu', 'capitalSince' : 1845 }"));
        states.insert((DBObject) JSON.parse("{ '_id' : '542969d3f21595edd9587065', '_class' : 'state', 'name' : 'Idaho', 'abbreviation' : 'ID', 'dateOfStatehood' : 1890, 'capital' : 'Boise', 'capitalSince' : 1865 }"));
        states.insert((DBObject) JSON.parse("{ '_id' : '542969d3f21595edd9587066', '_class' : 'state', 'name' : 'Illinois', 'abbreviation' : 'IL', 'dateOfStatehood' : 1818, 'capital' : 'Springfield', 'capitalSince' : 1837 }"));
        states.insert((DBObject) JSON.parse("{ '_id' : '542969d3f21595edd9587067', '_class' : 'state', 'name' : 'Indiana', 'abbreviation' : 'IN', 'dateOfStatehood' : 1816, 'capital' : 'Indianapolis', 'capitalSince' : 1825 }"));
        states.insert((DBObject) JSON.parse("{ '_id' : '542969d3f21595edd9587068', '_class' : 'state', 'name' : 'Iowa', 'abbreviation' : 'IA', 'dateOfStatehood' : 1846, 'capital' : 'Des Moines', 'capitalSince' : 1857 }"));
        states.insert((DBObject) JSON.parse("{ '_id' : '542969d3f21595edd9587069', '_class' : 'state', 'name' : 'Kansas', 'abbreviation' : 'KS', 'dateOfStatehood' : 1861, 'capital' : 'Topeka', 'capitalSince' : 1856 }"));
        states.insert((DBObject) JSON.parse("{ '_id' : '542969d3f21595edd958706a', '_class' : 'state', 'name' : 'Kentucky', 'abbreviation' : 'KY', 'dateOfStatehood' : 1792, 'capital' : 'Frankfort', 'capitalSince' : 1792 }"));
        states.insert((DBObject) JSON.parse("{ '_id' : '542969d3f21595edd958706b', '_class' : 'state', 'name' : 'Louisiana', 'abbreviation' : 'LA', 'dateOfStatehood' : 1812, 'capital' : 'Baton Rouge', 'capitalSince' : 1880 }"));
        states.insert((DBObject) JSON.parse("{ '_id' : '542969d3f21595edd958706c', '_class' : 'state', 'name' : 'Maine', 'abbreviation' : 'ME', 'dateOfStatehood' : 1820, 'capital' : 'Augusta', 'capitalSince' : 1832 }"));
        states.insert((DBObject) JSON.parse("{ '_id' : '542969d3f21595edd958706d', '_class' : 'state', 'name' : 'Maryland', 'abbreviation' : 'MD', 'dateOfStatehood' : 1788, 'capital' : 'Annapolis', 'capitalSince' : 1694 }"));
        states.insert((DBObject) JSON.parse("{ '_id' : '542969d3f21595edd958706e', '_class' : 'state', 'name' : 'Massachusetts', 'abbreviation' : 'MA', 'dateOfStatehood' : 1788, 'capital' : 'Boston', 'capitalSince' : 1630 }"));
        states.insert((DBObject) JSON.parse("{ '_id' : '542969d3f21595edd958706f', '_class' : 'state', 'name' : 'Michigan', 'abbreviation' : 'MI', 'dateOfStatehood' : 1837, 'capital' : 'Lansing', 'capitalSince' : 1847 }"));
        states.insert((DBObject) JSON.parse("{ '_id' : '542969d3f21595edd9587070', '_class' : 'state', 'name' : 'Minnesota', 'abbreviation' : 'MN', 'dateOfStatehood' : 1858, 'capital' : 'Saint Paul', 'capitalSince' : 1849 }"));
        states.insert((DBObject) JSON.parse("{ '_id' : '542969d3f21595edd9587071', '_class' : 'state', 'name' : 'Mississippi', 'abbreviation' : 'MS', 'dateOfStatehood' : 1817, 'capital' : 'Jackson', 'capitalSince' : 1821 }"));
        states.insert((DBObject) JSON.parse("{ '_id' : '542969d3f21595edd9587072', '_class' : 'state', 'name' : 'Missouri', 'abbreviation' : 'MO', 'dateOfStatehood' : 1821, 'capital' : 'Jefferson City', 'capitalSince' : 1826 }"));
        states.insert((DBObject) JSON.parse("{ '_id' : '542969d3f21595edd9587073', '_class' : 'state', 'name' : 'Montana', 'abbreviation' : 'MT', 'dateOfStatehood' : 1889, 'capital' : 'Helena', 'capitalSince' : 1875 }"));
        states.insert((DBObject) JSON.parse("{ '_id' : '542969d3f21595edd9587074', '_class' : 'state', 'name' : 'Nebraska', 'abbreviation' : 'NE', 'dateOfStatehood' : 1867, 'capital' : 'Lincoln', 'capitalSince' : 1867 }"));
        states.insert((DBObject) JSON.parse("{ '_id' : '542969d3f21595edd9587075', '_class' : 'state', 'name' : 'Nevada', 'abbreviation' : 'NV', 'dateOfStatehood' : 1864, 'capital' : 'Carson City', 'capitalSince' : 1861 }"));
        states.insert((DBObject) JSON.parse("{ '_id' : '542969d3f21595edd9587076', '_class' : 'state', 'name' : 'New Hampshire', 'abbreviation' : 'NH', 'dateOfStatehood' : 1788, 'capital' : 'Concord', 'capitalSince' : 1808 }"));
        states.insert((DBObject) JSON.parse("{ '_id' : '542969d3f21595edd9587077', '_class' : 'state', 'name' : 'New Jersey', 'abbreviation' : 'NJ', 'dateOfStatehood' : 1787, 'capital' : 'Trenton', 'capitalSince' : 1784 }"));
        states.insert((DBObject) JSON.parse("{ '_id' : '542969d3f21595edd9587078', '_class' : 'state', 'name' : 'New Mexico', 'abbreviation' : 'NM', 'dateOfStatehood' : 1912, 'capital' : 'Santa Fe', 'capitalSince' : 1610 }"));
        states.insert((DBObject) JSON.parse("{ '_id' : '542969d3f21595edd9587079', '_class' : 'state', 'name' : 'New York', 'abbreviation' : 'NY', 'dateOfStatehood' : 1788, 'capital' : 'Albany', 'capitalSince' : 1797 }"));
        states.insert((DBObject) JSON.parse("{ '_id' : '542969d3f21595edd958707a', '_class' : 'state', 'name' : 'North Carolina', 'abbreviation' : 'NC', 'dateOfStatehood' : 1789, 'capital' : 'Raleigh', 'capitalSince' : 1792 }"));
        states.insert((DBObject) JSON.parse("{ '_id' : '542969d3f21595edd958707b', '_class' : 'state', 'name' : 'North Dakota', 'abbreviation' : 'ND', 'dateOfStatehood' : 1889, 'capital' : 'Bismarck', 'capitalSince' : 1883 }"));
        states.insert((DBObject) JSON.parse("{ '_id' : '542969d3f21595edd958707c', '_class' : 'state', 'name' : 'Ohio', 'abbreviation' : 'OH', 'dateOfStatehood' : 1803, 'capital' : 'Columbus', 'capitalSince' : 1816 }"));
        states.insert((DBObject) JSON.parse("{ '_id' : '542969d3f21595edd958707d', '_class' : 'state', 'name' : 'Oklahoma', 'abbreviation' : 'OK', 'dateOfStatehood' : 1907, 'capital' : 'Oklahoma City', 'capitalSince' : 1910 }"));
        states.insert((DBObject) JSON.parse("{ '_id' : '542969d3f21595edd958707e', '_class' : 'state', 'name' : 'Oregon', 'abbreviation' : 'OR', 'dateOfStatehood' : 1859, 'capital' : 'Salem', 'capitalSince' : 1855 }"));
        states.insert((DBObject) JSON.parse("{ '_id' : '542969d3f21595edd958707f', '_class' : 'state', 'name' : 'Pennsylvania', 'abbreviation' : 'PA', 'dateOfStatehood' : 1787, 'capital' : 'Harrisburg', 'capitalSince' : 1812 }"));
        states.insert((DBObject) JSON.parse("{ '_id' : '542969d3f21595edd9587080', '_class' : 'state', 'name' : 'Rhode Island', 'abbreviation' : 'RI', 'dateOfStatehood' : 1790, 'capital' : 'Providence', 'capitalSince' : 1900 }"));
        states.insert((DBObject) JSON.parse("{ '_id' : '542969d3f21595edd9587081', '_class' : 'state', 'name' : 'South Carolina', 'abbreviation' : 'SC', 'dateOfStatehood' : 1788, 'capital' : 'Columbia', 'capitalSince' : 1786 }"));
    }

    private static MongoClient mongoClient() {
        try {
            return new MongoClient( "mongo-host" , 27017 );
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }
}
