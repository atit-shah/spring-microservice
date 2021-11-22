package com.learn.cloud.cart.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableJpaRepositories(basePackages = "com.learn.cloud.cart.dao.repository.cart", entityManagerFactoryRef = "cartDBEntityManagerFactory", transactionManagerRef = "cartDBTransactionManager")
public class CartDBConfig {

	@Value("${cartdb.jdbcUrl}")
	private String jdbcUrl;

	@Value("${cartdb.driverClassName}")
	private String driverClassName;

	@Value("${cartdb.username}")
	private String username;

	@Value("${cartdb.password}")
	private String password;

	@Value("${cartdb.hibernate.dialect}")
	private String hibernateDialect;

	@Bean(destroyMethod = "close")
	public DataSource cartDBDataSource() {
		HikariDataSource dataSource = new HikariDataSource();
		dataSource.setJdbcUrl(jdbcUrl);
		dataSource.setDriverClassName(driverClassName);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		return dataSource;
	}

	@Bean
	public JpaVendorAdapter cartDBJPAVendorAdapter() {
		HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
		adapter.setShowSql(true);
		return adapter;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean cartDBEntityManagerFactory() {
		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactoryBean.setDataSource(cartDBDataSource());
		entityManagerFactoryBean.setPackagesToScan("com.learn.cloud.cart.model");
		entityManagerFactoryBean.setJpaVendorAdapter(cartDBJPAVendorAdapter());
		Properties jpaProperties = new Properties();
		jpaProperties.setProperty("hibernate.dialect", hibernateDialect);
		entityManagerFactoryBean.setJpaProperties(jpaProperties);
		return entityManagerFactoryBean;
	}

	@Bean
	public PlatformTransactionManager cartDBTransactionManager() {
		JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
		jpaTransactionManager.setDataSource(cartDBDataSource());
		jpaTransactionManager.setEntityManagerFactory(cartDBEntityManagerFactory().getNativeEntityManagerFactory());
		return jpaTransactionManager;
	}

	@Bean
	public JdbcTemplate cartDBJdbcTemplate() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		jdbcTemplate.setDataSource(cartDBDataSource());
		return jdbcTemplate;
	}

}
