package com.dipnoi.ShortURL.resources;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.SecurityContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import com.dipnoi.ShortURL.model.RegisteredUrl;
import com.dipnoi.ShortURL.model.Statistics;
import com.dipnoi.ShortURL.service.securityServices.RegisterUrlService;

@Component
@Path("/")
public class RegisterUrlResource {
	
	private final RegisterUrlService regUrlService;
	
	@Autowired
	RegisterUrlResource(RegisterUrlService regUrlService){	
		this.regUrlService = regUrlService;
	}
	
	
	@POST
	@Path("/register")
	@Produces(MediaType.APPLICATION_JSON_VALUE)
	@Consumes(MediaType.APPLICATION_JSON_VALUE)
	public Response registerUrl(@Context SecurityContext securityContext, RegisteredUrl regUrl){
		
		//get username from autorization header
		String username = securityContext.getUserPrincipal().getName();
		
		regUrl = regUrlService.addUrl(username, regUrl);
		
		if (regUrl.getSuccess()){
			
			return Response.status(Status.CREATED)
					.entity(regUrl)
					.build();
		}
		
		return Response.status(Status.CONFLICT)
				.entity(regUrl)
				.build();		
	}
	
	
	@GET
	@Path("/statistics")
	@Produces(MediaType.APPLICATION_JSON_VALUE)
	public Response getStats(@Context SecurityContext securityContext){
		
		//get username from autorization header
		String username = securityContext.getUserPrincipal().getName();
		
		HashMap<String, BigInteger> statistics = regUrlService.getStatistics(username);
		
		return Response.status(Status.OK)
					   .entity(statistics)
				       .build();
	}
}
