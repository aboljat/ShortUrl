package com.dipnoi.ShortURL.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.savedrequest.NullRequestCache;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
	public void configure(WebSecurity web) throws Exception {
	    web.ignoring().antMatchers("/ShortURL/account").antMatchers("/ShortURL/help");
	}


	@Override
	protected void configure(HttpSecurity http) throws Exception {
	    http
	      .authorizeRequests()
	      .antMatchers("/ShortURL/account").permitAll()
	      .antMatchers("/ShortURL/help").permitAll()
	      .anyRequest().authenticated()
	      .and()
	      .httpBasic()
	      .and()
	      .headers().frameOptions().disable()
	      .and()
	      .requestCache()
	      .requestCache(new NullRequestCache())
	      .and()
	      .csrf().disable();
	}

	
}


