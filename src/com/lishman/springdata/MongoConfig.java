package com.lishman.springdata;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.MongoClient;
import com.mongodb.WriteConcern;

@Configuration
@Profile("mongodb")
public class MongoConfig extends AbstractMongoConfiguration {
    
    // ---------------------------------------------------- MongoDb

    @Override
    protected String getDatabaseName() {
        return "world";
    }

    @Override
    @Bean
    public MongoClient mongo() throws Exception {
        MongoClient client = new MongoClient("mongo-host");
        client.setWriteConcern(WriteConcern.SAFE);
        return client;
    }
    
    // avoid reflection lookups
    @Override
    protected String getMappingBasePackage() {
        return "com.lishman.springdata.mongo.domain";
    }

    // ---------------------------------------------------- Test Data

    @Bean
    public MongoTestData getTestMongoDbData() {
        return new MongoTestData();
    }
    
    // ---------------------------------------------------- Repository

    @Configuration
    @Profile("repo")
    @EnableMongoRepositories(basePackages="com.lishman.springdata.mongo.repository")
    public static class MongoRepositoryConfig {}

    // ---------------------------------------------------- DAO
    
    @Configuration
    @Profile("dao")
    @ComponentScan("com.lishman.springdata.mongo.dao")
    public static class MongoDaoConfig {}
    
}