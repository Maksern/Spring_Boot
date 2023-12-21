package com.example.demo.Configuration;

import javax.sql.DataSource;

import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseConfiguration {
    
    @Bean
	public DataSource getDataSource() {
    	PGSimpleDataSource dataSource = new PGSimpleDataSource();
    	dataSource.setURL("jdbc:postgresql://localhost:5432/");
    	dataSource.setUser("postgres");
    	dataSource.setPassword("postgres");
    	dataSource.setDatabaseName("Sport_Competitions");

        return dataSource;
	}
}
