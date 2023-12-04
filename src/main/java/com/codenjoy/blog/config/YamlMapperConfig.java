package com.codenjoy.blog.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import com.fasterxml.jackson.dataformat.yaml.util.StringQuotingChecker;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import static com.fasterxml.jackson.databind.PropertyNamingStrategies.SNAKE_CASE;
import static com.fasterxml.jackson.dataformat.yaml.YAMLGenerator.Feature.MINIMIZE_QUOTES;
import static com.fasterxml.jackson.dataformat.yaml.YAMLGenerator.Feature.WRITE_DOC_START_MARKER;

@Component
public class YamlMapperConfig {

    @Bean
    @Qualifier("yamlMapper")
    public YAMLMapper yamlMapper() {
        YAMLFactory factory = YAMLFactory.builder()
                .stringQuotingChecker(new StringQuotingChecker() {
                    @Override
                    public boolean needToQuoteName(String name) {
                        return false;
                    }

                    @Override
                    public boolean needToQuoteValue(String value) {
                        return false;
                    }
                })
                .build();

        return YAMLMapper.builder(factory)
                .disable(WRITE_DOC_START_MARKER)
                .enable(MINIMIZE_QUOTES)
                .propertyNamingStrategy(SNAKE_CASE)
                .build();
    }

    @Bean
    @Primary
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}