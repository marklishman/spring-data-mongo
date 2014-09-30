package com.lishman.springdata;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.net.UnknownHostException;

import org.junit.Before;
import org.junit.Test;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

import com.lishman.springdata.domain.Continent;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

// TODO Custom mapping converter?

public class ManualSetupTest {
    
    private MongoClient client;
    
    @Before
    public void before() throws UnknownHostException {
        client = new MongoClient("mongo-host");
    }
    
    @Test
    public void standaloneTemplateUsage() {
        MongoOperations ops = new MongoTemplate(client, "world");
        assertThat(ops.getCollection("continents").count(), equalTo(7L));
        
    }
    
    @Test
    public void usingMappingSubSystemProgramatically() throws UnknownHostException {
        MongoMappingContext context = new MongoMappingContext();
        MongoDbFactory factory = new SimpleMongoDbFactory(client, "world");
        MappingMongoConverter converter = new MappingMongoConverter(factory, context);

        DBObject asia = new BasicDBObject();
        converter.write(new Continent(1, "Asia"), asia);
        System.out.println(asia);
        
    }

}
