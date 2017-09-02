package io.lishman.springdata.config;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.WriteConcern;
import io.lishman.springdata.repository.RepositoryPackage;
import io.lishman.springdata.template.TemplatePackage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

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
        MongoClientOptions options = MongoClientOptions.builder()
                .writeConcern(WriteConcern.JOURNALED)
                .build();
        return new MongoClient("mongo", options);
    }
    
    // ---------------------------------------------------- MongoTemplate
    
    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        return new MongoTemplate(mongo(), getDatabaseName());
    }
    
}
