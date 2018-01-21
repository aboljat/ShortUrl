package com.dipnoi.ShortURL.service.securityServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dipnoi.ShortURL.model.RegisteredUrl;
import com.dipnoi.ShortURL.repository.RegisterUrlRepository;


@Service
public class RedirectService {
	
	private final RegisterUrlRepository regUrlRep;

	
	@Autowired
	public RedirectService(RegisterUrlRepository regUrlRep){
		
		this.regUrlRep = regUrlRep;

	}
	
	public RegisteredUrl getInitialUrl(String shortUrl){
		
		if(regUrlRep.existsByShortUrl(shortUrl)){
			
			regUrlRep.updateCount(shortUrl);
			return regUrlRep.getByShortUrl(shortUrl);
		}
		return null;
	}

}
