package com.dipnoi.ShortURL.config.regUrlConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.apache.commons.validator.routines.UrlValidator;

@Configuration
public class RegisterUrlConfig {
	
	@Bean
	public UrlValidator getUrlValidator(){
		
		return new UrlValidator();
	}

}



