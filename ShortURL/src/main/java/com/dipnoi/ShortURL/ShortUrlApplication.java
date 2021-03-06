package com.dipnoi.ShortURL;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.dipnoi.ShortURL.repository")
@EntityScan("com.dipnoi.ShortURL.model")
@ComponentScan(basePackages = {"com.dipnoi.ShortURL.*"})
public class ShortUrlApplication extends SpringBootServletInitializer{
	
	
	public static void main(String[] args) {
		SpringApplication.run(ShortUrlApplication.class, args);
	}	
	
}
