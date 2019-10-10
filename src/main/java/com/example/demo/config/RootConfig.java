package com.example.demo.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class RootConfig {

	@Value("${spring.datasource.driver-class-name}")
	private String postgresqlDriverClassName;
	@Value("${spring.datasource.url}")
	private String postgresqlUrl;
	@Value("${spring.datasource.username}")
	private String postgresqlUserName;
	@Value("${spring.datasource.password}")
	private String postgresqlPassword;
	@Value("${spring.datasource.hikari.maximum-pool-size}")
	private Integer postgresqlMaximumPoolSize;
	@Value("${spring.datasource.hikari.minimum-idle}")
	private Integer postgresqlMinimumIdle;

	@Bean
	public DataSource dataSource() {
		HikariDataSource dataSource = new HikariDataSource();
		dataSource.setDriverClassName(postgresqlDriverClassName);
		dataSource.setJdbcUrl(postgresqlUrl);
		dataSource.setUsername(postgresqlUserName);
		dataSource.setPassword(postgresqlPassword);
		dataSource.setMaximumPoolSize(postgresqlMaximumPoolSize);
		dataSource.setMinimumIdle(postgresqlMinimumIdle);
	    return dataSource;
	}
}
