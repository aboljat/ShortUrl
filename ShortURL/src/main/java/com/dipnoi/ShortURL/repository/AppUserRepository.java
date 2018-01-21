package com.dipnoi.ShortURL.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.dipnoi.ShortURL.model.AppUser;

@Repository
public interface AppUserRepository extends JpaRepository <AppUser, Long>{
	
	
	/**
	 * Find a user by username
	 *
	 * @param username the user's username
	 * @return user which contains the user with the given username or null.
	 */
	AppUser findOneByUsername(String username);
	
	
	
	/**
	 * Check if entity with given username exists
	 * @param username
	 * @return
	 */
	boolean existsByUsername(String username);
	
}


