package com.dipnoi.ShortURL.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Model for statistics, also linked to register_url,
 * so we can retrieve results from custom querry
 * 
 * @author dipnoi
 *
 */
@Component
@Entity
@Table(name = "registered_url")
@Immutable
public class Statistics{
	
	
	@Id
	@Column(name = "url_id", nullable = false, updatable = false, unique = true)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long urlId;
	
	@Column(name = "initial_url", nullable = false, unique = true)
	private String initialUrl;
	
	@Column(name = "counter")
	private long counter;
	
	
	public String getInitialUrl() {
		return initialUrl;
	}
	
	public void setInitialUrl(String initialUrl) {
		this.initialUrl = initialUrl;
	}
	
	public Long getCounter() {
		return counter;
	}
	
	public void setCounter(long counter) {
		this.counter = counter;
	}

}
