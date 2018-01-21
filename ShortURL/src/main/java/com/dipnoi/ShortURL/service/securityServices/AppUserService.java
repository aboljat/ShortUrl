package com.dipnoi.ShortURL.service.securityServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.RandomStringUtils;

import com.dipnoi.ShortURL.model.AppUser;
import com.dipnoi.ShortURL.repository.AppUserRepository;

@Service
public class AppUserService implements UserDetailsService{

	
	private final AppUserRepository appUseRep;
	
	private static final String OK_REGISTRATION = "User succesfully created!";
	private static final String BAD_REGISTRATION = "User already exists!";
	
	@Autowired
	public AppUserService(AppUserRepository appUseRep){
		
		this.appUseRep = appUseRep;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		return appUseRep.findOneByUsername(username);

	}
	
	public AppUser addUser(AppUser appUser){
		
		if(!appUseRep.existsByUsername(appUser.getUsername())){
			
			appUser.setDescription(OK_REGISTRATION);
			appUser.setSuccess(true);
			appUser.setPassword(RandomStringUtils.randomAlphanumeric(8));
			appUser = appUseRep.save(appUser);
			appUseRep.flush();
		return appUser;
		}
		appUser.setDescription(BAD_REGISTRATION);
		appUser.setSuccess(false);
		return appUser;
	}
}

