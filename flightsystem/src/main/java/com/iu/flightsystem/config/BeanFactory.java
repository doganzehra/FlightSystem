package com.iu.flightsystem.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class BeanFactory {
	
	@Bean(name = "mydatasource")
    public DataSource getDatasource() {
        // datasource = connection bilgileri
        DriverManagerDataSource datasource = new DriverManagerDataSource("jdbc:postgresql://localhost:5432/FLIGHT_SYSTEM",
                "postgres", "123");
        datasource.setDriverClassName("org.postgresql.Driver");
        return datasource;
    }

   @Bean(name = "txManager")
    @DependsOn(value = "mydatasource")
    public DataSourceTransactionManager getManager(@Autowired DataSource ds) {
        return new DataSourceTransactionManager(ds);
    }

   @Bean
    // öncelikle mydatasource isimli bean oluşturulmuş olmalı
    @DependsOn(value = "mydatasource")
    public JdbcTemplate getJDBCTemplate(@Autowired DataSource ds) {
        return new JdbcTemplate(ds);
    }

   @Bean
    // öncelikle mydatasource isimli bean oluşturulmuş olmalı
    @DependsOn(value = "mydatasource")
    public NamedParameterJdbcTemplate getNamedParameterJDBCTemplate(@Autowired DataSource ds) {
        return new NamedParameterJdbcTemplate(ds);
    }
}
