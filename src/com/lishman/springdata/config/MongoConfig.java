package com.lishman.springdata.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.lishman.springdata.MongoTestData;
import com.lishman.springdata.repository.RepositoryPackage;
import com.mongodb.MongoClient;
import com.mongodb.WriteConcern;

@Configuration
@ComponentScan(basePackageClasses=RepositoryPackage.class)
@EnableMongoRepositories(basePackageClasses=RepositoryPackage.class)
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

    @Override
    protected String getMappingBasePackage() {
        return "com.lishman.springdata.domain";
    }

    // ---------------------------------------------------- Test Data

    @Bean
    public MongoTestData getTestMongoDbData() {
        return new MongoTestData();
    }
    
}