package com.codenjoy.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import static java.nio.charset.StandardCharsets.UTF_8;

@Configuration
@Profile("nocache")
public class ThymeleafConfig {

    @Bean
    public SpringResourceTemplateResolver templateResolver() {
        return new SpringResourceTemplateResolver() {{
            setPrefix("file:src/main/resources/templates/");
            setSuffix(".html");
            setTemplateMode("HTML5");
            setCharacterEncoding(UTF_8.name());
            setCacheable(false);
        }};
    }

    @Bean
    public SpringTemplateEngine templateEngine() {
        return new SpringTemplateEngine() {{
            setTemplateResolver(templateResolver());
        }};
    }

    @Bean
    public ThymeleafViewResolver viewResolver() {
        return new ThymeleafViewResolver() {{
            setTemplateEngine(templateEngine());
        }};
    }
}