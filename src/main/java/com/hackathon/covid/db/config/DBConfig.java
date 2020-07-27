package com.hackathon.covid.db.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;

import org.springframework.context.annotation.Primary;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

import org.springframework.orm.jpa.JpaTransactionManager;

import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import org.springframework.transaction.PlatformTransactionManager;

import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration

@EnableTransactionManagement

//@EnableJpaRepositories(basePackages = { "com.hackathon.covid.assessment.repo" })

@EntityScan(basePackages = { "com.hackathon.covid.assessment.entities" })

@EnableJpaRepositories(basePackages = {
		"com.hackathon.covid.assessment.repo" }, entityManagerFactoryRef = "sourceEntityManager", transactionManagerRef = "sourceTransactionManager")

public class DBConfig {

	@Value("${spring.datasource.url}")

	String databaseUrl;

	@Value("${spring.datasource.username}")

	String databaseUser;

	@Value("${spring.datasource.password}")

	String databasePassword;

	@Value("${spring.datasource.driver-class}")

	String databaseDriver;

	private static final Logger log = LoggerFactory.getLogger(DBConfig.class);

	@Bean

	@Primary

	public DataSource dataSource() {

		DriverManagerDataSource dataSource = new DriverManagerDataSource();

		dataSource.setDriverClassName(databaseDriver);

		dataSource.setUrl(databaseUrl);

		dataSource.setUsername(databaseUser);

		dataSource.setPassword(databasePassword);

		return dataSource;

	}

	@Primary

	@Bean(name = "sourceEntityManager")

	public LocalContainerEntityManagerFactoryBean sourceEntityManager() {

		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();

		String[] scanPackages = new String[] { "com.hackathon.covid.assessment.entities" };

		DataSource dataSource = this.dataSource();

		if (dataSource != null) {

			em.setDataSource(dataSource);

			em.setPackagesToScan(scanPackages);

			HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();

			Properties jpaProperties = new Properties();

			jpaProperties.put("hibernate.connection.release_mod", "after_transaction");

			jpaProperties.put("hibernate.show_sql", false);

			jpaProperties.put("hibernate.format_sql", false);

			jpaProperties.put("hibernate.generate_statistics", false);

			em.setJpaProperties(jpaProperties);

			em.setJpaVendorAdapter(vendorAdapter);

			return em;

		} else {

			log.error("Unable to get the datasource. Check the application logs");

			return null;

		}

	}

	@Primary

	@Bean(name = "sourceTransactionManager")

	public PlatformTransactionManager sourceTransactionManager() {

		JpaTransactionManager transactionManager = new JpaTransactionManager();

		transactionManager.setEntityManagerFactory(sourceEntityManager().getObject());

		return transactionManager;

	}

}