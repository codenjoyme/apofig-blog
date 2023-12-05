package com.codenjoy.blog.config;

import com.codenjoy.blog.service.ProfileStatus;
import lombok.extern.slf4j.Slf4j;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Configuration
public class StartupConfig {

    @Value("${server.port}")
    private String port;

    @Value("${delete.secret}")
    private String secret;

    @Autowired
    private ProfileStatus profile;

    @Value("${server.servlet.context-path}")
    private String contextPath;

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String user;

    @Value("${spring.datasource.password}")
    private String password;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public ApplicationRunner startupRunner(RestTemplate rest) {
        return args -> {
            if (profile.isEnabled("test")) {
                return;
            }

            // this will update database schema
            Flyway.configure()
                    .dataSource(url, user, password)
                    .load()
                    .migrate();

            // we need this because of FileLogger is working in the HTTP context only,
            // and we need to get all pages before any HTTP request
            get(rest, "api/init");

            log.info("Started!");
        };
    }

    private void get(RestTemplate rest, String apiUrl) {
        String url = String.format("http://localhost:%s/%s/%s?secret=%s",
                port, contextPath, apiUrl, secret);
        rest.getForEntity(url, Void.class);
    }
}