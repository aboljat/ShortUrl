package com.dipnoi.ShortURL.service.securityServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dipnoi.ShortURL.model.RegisteredUrl;
import com.dipnoi.ShortURL.repository.RegisterUrlRepository;

/**
 * Service used to redirect url
 * 
 * @author dipnoi
 *
 */
@Service
public class RedirectService {
	
	private final RegisterUrlRepository regUrlRep;

	
	@Autowired
	public RedirectService(RegisterUrlRepository regUrlRep){
		
		this.regUrlRep = regUrlRep;

	}
	
	/**
	 * Method gets initial url using provided shortUrl
	 * and increments count
	 * 
	 * @param shortUrl
	 * @return
	 */
	public RegisteredUrl getInitialUrl(String shortUrl){
		
		if(regUrlRep.existsByShortUrl(shortUrl)){
			
			regUrlRep.updateCount(shortUrl);
			return regUrlRep.getByShortUrl(shortUrl);
		}
		return null;
	}

}
