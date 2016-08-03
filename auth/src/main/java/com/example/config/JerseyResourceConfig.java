package com.example.config;

import com.example.user.resources.UserResource;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.ws.rs.ApplicationPath;

/**
 * Created by IBM on 2016/8/1.
 * jersey filter
 */
@ApplicationPath("/webapi")
@Component
public class JerseyResourceConfig extends ResourceConfig {

    public JerseyResourceConfig() {

        //packages("com.example.user.resources");
        //register(JacksonFeature.class);
        register(UserResource.class);
    }
}
