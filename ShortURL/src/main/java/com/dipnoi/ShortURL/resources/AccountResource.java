package com.dipnoi.ShortURL.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import com.dipnoi.ShortURL.model.AppUser;
import com.dipnoi.ShortURL.service.securityServices.AppUserService;

@Component
@Path("/account")
public class AccountResource {
	
	private final AppUserService appUserService;
	
	@Autowired
	AccountResource(AppUserService appUserService){	
		this.appUserService = appUserService;
	}
	
	
	@POST
	@Produces(MediaType.APPLICATION_JSON_VALUE)
	@Consumes(MediaType.APPLICATION_JSON_VALUE)
	public Response getRegostration(AppUser appUser){
		
		appUser = appUserService.addUser(appUser);
		
		if (appUser.getSuccess()){
			
			return Response.status(Status.CREATED)
					.entity(appUser)
					.build();
		}
		
		return Response.status(Status.CONFLICT)
				.entity(appUser)
				.build();
		
	}

}
 	 	