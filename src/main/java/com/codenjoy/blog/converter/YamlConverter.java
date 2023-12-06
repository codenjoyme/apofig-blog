package com.codenjoy.blog.converter;

import com.codenjoy.blog.dto.PageSettings;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import static org.apache.commons.lang3.StringUtils.removeEnd;

@Slf4j
@Component
public class YamlConverter {

    private ObjectMapper mapper;

    public YamlConverter(@Qualifier("yamlMapper") ObjectMapper mapper) {
        this.mapper = mapper;
    }

    public PageSettings from(String props) {
        return from(props, PageSettings.class);
    }

    public <T> T from(String props, Class<T> type) {
        try {
            return mapper.readValue(props, type);
        } catch (JsonProcessingException exception) {
            String msg = String.format("Error while parsing %s properties:\n%s",
                    type, props);
            log.error(msg, exception);
            throw new IllegalArgumentException(msg, exception);
        }
    }

    public <T> String to(T props) {
        try {
            return removeEnd(mapper.writeValueAsString(props), "\n");
        } catch (JsonProcessingException exception) {
            String msg = String.format("Error while mapping %s properties:\n%s",
                    props.getClass(), props);
            log.error(msg, exception);
            throw new IllegalArgumentException(msg, exception);
        }
    }
}