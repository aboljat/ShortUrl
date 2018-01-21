package com.dipnoi.ShortURL.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.dipnoi.ShortURL.model.RegisteredUrl;
import com.dipnoi.ShortURL.model.Statistics;

public interface RegisterUrlRepository extends JpaRepository <RegisteredUrl, Long>{
	
	
	/**
	 * Check if entity with given long url exists
	 * @param initialUrl
	 * @return
	 */
	boolean existsByInitialUrl(String initialUrl);
	
	
	/**
	 * Check if entity with given short url exists
	 * @param shortUrl
	 * @return
	 */
	boolean existsByShortUrl(String shortUrl);
	
	
	/**
	 * Get RegisteredUrl object by providing shortUrl
	 * 
	 * @param shortUrl
	 * @return
	 */
	RegisteredUrl getByShortUrl(String shortUrl);
	
	
	/**
	 * custom query to get statistics for user
	 * 
	 * @param username
	 * @return
	 */
	@Query(value = "SELECT r.initial_url, r.counter FROM registered_url r WHERE r.user_id ="
			+ " (SELECT a.user_id FROM app_user a WHERE user_name= ?1)", nativeQuery=true)
	List<Object[]> getStats(String username);
	
	/**
	 * Custome query, used to increment count
	 * 
	 * @param shortUrl
	 */
	@Modifying
	@Transactional
	@Query(value = "UPDATE registered_url SET counter = counter + 1 WHERE short_url = ?1", nativeQuery=true)
	void updateCount(String shortUrl);

}
