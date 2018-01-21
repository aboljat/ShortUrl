package com.dipnoi.ShortURL.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@Component
@Entity
@Table(name = "registered_url")
@JsonIgnoreProperties(value = {"url_id", "user_id", "short_url", "count"}, allowGetters = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RegisteredUrl{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "url_id", nullable = false, updatable = false, unique = true)
	private Long urlId;
	
	@Column(name = "user_id", nullable = false, updatable = false)
	private Long userId;
	
	@Column(name = "initial_url", nullable = false, unique = true)
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String initialUrl;
	
	@Column(name = "short_url", nullable = false)
	private String shortUrl;
	
	@Column(name = "redirect_type", nullable = false)
	private Integer redirectType;
	
	@Column(name = "counter")
	private Long counter;
	
	
	@JsonIgnore
    public Long getCounter() {
		return counter;
	}
    
    @JsonIgnore
	public void setCounter(Long counter) {
		this.counter = counter;
	}

	private boolean success;
	
	private String description;
	
	@JsonIgnore
	public Long getUrlId() {
		return urlId;
	}
	
	@JsonIgnore
	public Long getUserId() {
		return userId;
	}
	

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	

	public String getInitialUrl() {
		return initialUrl;
	}

	public void setInitialUrl(String initialUrl) {
		this.initialUrl = initialUrl;
	}
	
	public String getShortUrl() {
		return shortUrl;
	}
	
	public void setShortUrl(String shortUrl) {
		this.shortUrl = shortUrl;
	}
	
	public void setRedirectType(Integer redirectType){
		
		this.redirectType = redirectType;
	}
	
	public Integer getRedirectType() {
		return redirectType;
	}
	
	public boolean getSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
