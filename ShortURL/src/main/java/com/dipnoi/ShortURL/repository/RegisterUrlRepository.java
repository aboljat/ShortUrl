package com.dipnoi.ShortURL.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.dipnoi.ShortURL.model.RegisteredUrl;
import com.dipnoi.ShortURL.model.Statistics;

public interface RegisterUrlRepository extends JpaRepository <RegisteredUrl, Long>{
	
	
	boolean existsByInitialUrl(String initialUrl);
	
	boolean existsByShortUrl(String shortUrl);
	
	RegisteredUrl getByShortUrl(String shortUrl);
	
	@Query(value = "SELECT r.initial_url, r.counter FROM registered_url r WHERE r.user_id ="
			+ " (SELECT a.user_id FROM app_user a WHERE user_name= ?1)", nativeQuery=true)
	List<Object[]> getStats(String username);
	
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE registered_url SET counter = counter + 1 WHERE short_url = ?1", nativeQuery=true)
	void updateCount(String shortUrl);

}
