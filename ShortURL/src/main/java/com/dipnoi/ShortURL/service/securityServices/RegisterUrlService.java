package com.dipnoi.ShortURL.service.securityServices;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dipnoi.ShortURL.model.AppUser;
import com.dipnoi.ShortURL.model.RegisteredUrl;
import com.dipnoi.ShortURL.repository.AppUserRepository;
import com.dipnoi.ShortURL.repository.RegisterUrlRepository;

/**
 * Service used to register new url
 * 
 * @author dipnoi
 *
 */
@Service
public class RegisterUrlService {
	
	private final RegisterUrlRepository regUrlRep;
	private final AppUserRepository appUsrRep;
	private final UrlValidator urlValidator;
	
	//response messages
	private static final String OK_REGISTRATION = "URL succesfully registered!";
	private static final String BAD_REGISTRATION = "URL already registered!";
	private static final String BAD_URL = "Provided URL is not valid!";
	
	@Autowired
	public RegisterUrlService(RegisterUrlRepository regUrlRep, AppUserRepository appUsrRep, UrlValidator urlValidator){
		
		this.appUsrRep = appUsrRep;
		this.regUrlRep = regUrlRep;
		this.urlValidator = urlValidator;
	}
	

	/**
	 * Method is used to register new url, new url is saved in database,
	 * and short url is constructed. Url is validated and check is made t
	 * to see if url is already registered. 
	 * @param username
	 * @param regUrl
	 * @return RegisteredURL or null - if url is already registered
	 */
	public RegisteredUrl addUrl(String username, RegisteredUrl regUrl){
		
		//check if provided url is valid
		if(!urlValidator.isValid(regUrl.getInitialUrl())){
			
			regUrl.setDescription(BAD_URL);
			regUrl.setSuccess(false);
			regUrl.setRedirectType(0);
			return regUrl;	
		}
		
		//check if url is already registered
		if(!regUrlRep.existsByInitialUrl(regUrl.getInitialUrl())){
			
			AppUser ap = appUsrRep.findOneByUsername(username);
			
			//id of user who registered url
			regUrl.setUserId(ap.getId());
			regUrl.setShortUrl(generateShort());
			regUrl.setCounter(Long.valueOf(0));
			
			if(regUrl.getRedirectType() == null){
				
				regUrl.setRedirectType(Integer.valueOf(302));
			}
			regUrl.setDescription(OK_REGISTRATION);
			regUrl.setSuccess(true);
			regUrl = regUrlRep.save(regUrl);
			regUrlRep.flush();
			
		return regUrl;
		}
		regUrl.setDescription(BAD_REGISTRATION);
		regUrl.setSuccess(false);
		regUrl.setRedirectType(null);
		return regUrl;
	}
	
	/**
	 * Method returns hash map containing all url-s registered by user,
	 * together with counter
	 * 
	 * @param username
	 * @return
	 */
	
	public HashMap<String, BigInteger> getStatistics(String username){
		
		List<Object[]> list = regUrlRep.getStats(username);
		HashMap<String, BigInteger> map = new HashMap<>();
		
		//nesto
		for(Object[] stat : list){
			
			map.put((String)stat[0], (BigInteger)stat[1]);
			
			
		}
		return map;
	}
	
	
	private String generateShort(){
		
		String shortUrl = RandomStringUtils.randomAlphabetic(6);
		
		//if exists call method again until unique shortUrl is returned
		if(regUrlRep.existsByInitialUrl(shortUrl)){
			
			generateShort();
			
		}
		return shortUrl;
	}

}
