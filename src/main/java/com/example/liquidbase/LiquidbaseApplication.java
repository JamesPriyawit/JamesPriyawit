package com.example.liquidbase;

import liquibase.integration.spring.SpringLiquibase;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

@SpringBootApplication
public class LiquidbaseApplication {

	public static void main(String[] args) {
		System.setProperty("liquibase.databaseChangeLogTableName", "DATABASECHANGELOG");
		System.setProperty("liquibase.databaseChangeLogLockTableName", "DATABASECHANGELOGLOCK");
		SpringApplication.run(LiquidbaseApplication.class, args);
	}

	@Bean
	public SpringLiquibase liquibase(DataSource dataSource){

		SpringLiquibase liquibase = new SpringLiquibase();

		liquibase.setDataSource(dataSource);
		liquibase.setContexts("dev");
		liquibase.setChangeLog("classpath:db-changelog.xml");

		return liquibase;
	}

}
