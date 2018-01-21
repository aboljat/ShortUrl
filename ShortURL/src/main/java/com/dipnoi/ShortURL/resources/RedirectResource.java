package com.dipnoi.ShortURL.resources;

import java.net.URI;
import java.net.URISyntaxException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dipnoi.ShortURL.model.RegisteredUrl;
import com.dipnoi.ShortURL.service.securityServices.RedirectService;


/**
 * Class is used to expose end point for 
 * url redirection
 * 
 * @author dipnoi
 *
 */
@Component
@Path("/")
public class RedirectResource {
	
private final RedirectService redService;
	
	@Autowired
	RedirectResource(RedirectService redService){	
		this.redService = redService;
	}
	
	
	/**
	 * Redirects from short to original url
	 * Method calls Redirect service to retrieve RegisteredUrl object
	 * if object is null not found is returned and else
	 * user is redirected according to redirect type
	 * 
	 * @param shortUrl
	 * @return Response
	 * @throws URISyntaxException
	 */
	@GET
	@Path("/{shortUrl}")
	public Response redirectRequest(@PathParam("shortUrl") String shortUrl) throws URISyntaxException{
		
		RegisteredUrl regUrl = redService.getInitialUrl(shortUrl);
		
		if(regUrl == null){
			
			return Response.status(Status.NOT_FOUND).build();
		}
		
		URI uri = new URI(regUrl.getInitialUrl());
		
		if(regUrl.getRedirectType().equals((301))){
			
			return Response.status(Status.MOVED_PERMANENTLY).location(uri).build();
		}
		
		return Response.status(Status.FOUND).location(uri).build();
	}

}
