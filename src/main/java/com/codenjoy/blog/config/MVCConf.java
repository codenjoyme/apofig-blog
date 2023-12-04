package com.codenjoy.blog.config;

import com.codenjoy.blog.service.ProfileStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static com.codenjoy.blog.service.ProfileStatus.NO_CACHE;

@Configuration
public class MVCConf implements WebMvcConfigurer {

    public static final String RESOURCES_URI = "/static/**";

    @Autowired
    private ProfileStatus profile;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        ResourceHandlerRegistration handler = registry.addResourceHandler(RESOURCES_URI);
        if (profile.isEnabled(NO_CACHE)) {
            handler.addResourceLocations(
                    "file:src/main/resources/static/",
                    "file:target/classes/static/");
        }
        handler.addResourceLocations(
                "classpath:/static/",
                "classpath:/META-INF/resources/webjars/");
    }
}