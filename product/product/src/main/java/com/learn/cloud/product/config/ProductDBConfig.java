package com.learn.cloud.product.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(mongoTemplateRef = "productMongoTemplate", basePackages = "com.learn.cloud.product.dataaccess.repository")
public class ProductDBConfig {

	@Value("${mongo.host:localhost}")
	private String mongoHost;

	@Value("${mongo.port:27017}")
	private String mongoPort;

	@Value("${mongo.db:product}")
	private String mongoDB;

	@Bean
	public MongoDatabaseFactory mongoDbFactory() {
		return new SimpleMongoClientDatabaseFactory("mongodb://" + mongoHost + ":" + mongoPort + "/" + mongoDB);
	}

	@Bean
	public MongoTemplate productMongoTemplate() {
		return new MongoTemplate(mongoDbFactory());
	}

}
