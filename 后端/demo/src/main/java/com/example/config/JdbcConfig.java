package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class JdbcConfig {

    @Bean
    public static DataSource dataSource1(){
        DriverManagerDataSource dataSource=new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/codeforces");
        dataSource.setUsername("root");
        dataSource.setPassword("");
        return dataSource;
    }
    @Bean
    public JdbcTemplate jdbcTemplate1(DataSource dataSource1) {
        return new JdbcTemplate(dataSource1);
    }

}
