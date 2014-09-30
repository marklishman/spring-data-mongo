package com.lishman.springdata.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.lishman.springdata.repository.RepositoryPackage;
import com.lishman.springdata.template.TemplatePackage;
import com.lishman.springdata.testdata.TestDataPackage;
import com.mongodb.MongoClient;
import com.mongodb.WriteConcern;

// TODO JavaConfig - page 90
/* Include XML configuration.
 */

@Configuration
@EnableMongoRepositories(basePackageClasses=RepositoryPackage.class)
@ComponentScan(basePackageClasses=TemplatePackage.class)
public class MongoConfig extends AbstractMongoConfiguration {
    
    // ---------------------------------------------------- mongodb config

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
    
    // ---------------------------------------------------- MongoTemplate
    
    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        return new MongoTemplate(mongo(), getDatabaseName());
    }
    
    // ---------------------------------------------------- test data

    // TODO still required?
    @Configuration
    @ComponentScan(basePackageClasses={TestDataPackage.class})
    @Profile("test")
    static class TestData {}
    
}
