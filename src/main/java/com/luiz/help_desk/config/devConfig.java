package com.luiz.help_desk.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

import com.luiz.help_desk.services.DBService;

@Configuration
@Profile("dev")
@PropertySource("classpath:application_dev.properties")
@ComponentScan("com.luiz.help_desk")
public class devConfig {

	@Autowired
	private DBService dbService;
	
	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String value;
	
	@Bean
	public boolean instanciaDB() {
		
		if (value.equals("create")) {
			this.dbService.instanciaDB();
		}
		
		return false;
		
	}
	
}
