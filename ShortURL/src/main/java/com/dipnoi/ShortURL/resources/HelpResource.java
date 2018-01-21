package com.dipnoi.ShortURL.resources;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;


/**
 * Class used to diplay help page
 * 
 * @author dipnoi
 *
 */
@Component
@Path("/help")
public class HelpResource {
	
	@Autowired
    ResourceLoader resourceLoader;
	
	@GET
	@Produces(MediaType.TEXT_HTML_VALUE)
    public Response help() throws IOException {
		
		Resource fileResource = resourceLoader.getResource("classpath:static/help.html");
		
		InputStream inputStream = fileResource.getInputStream();
		
		Scanner sc = new Scanner(inputStream, "UTF-8");
		String string =  sc.useDelimiter("\\A").next();
		sc.close();
	
		return Response.status(Status.CREATED)
				.entity(string)
				.build();
			
    }
	
}
