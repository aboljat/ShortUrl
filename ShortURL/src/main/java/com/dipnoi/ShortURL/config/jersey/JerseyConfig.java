package com.dipnoi.ShortURL.config.jersey;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletProperties;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.dipnoi.ShortURL.resources.RegisterUrlResource;
import com.dipnoi.ShortURL.resources.AccountResource;
import com.dipnoi.ShortURL.resources.HelpResource;
import com.dipnoi.ShortURL.resources.RedirectResource;


@Configuration
@Component
@EnableAutoConfiguration
@ApplicationPath("/ShortURL")
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        register(AccountResource.class);
        register(RegisterUrlResource.class);
        register(RedirectResource.class);
        register(HelpResource.class);
    }
}
