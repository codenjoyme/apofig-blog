package com.codenjoy.blog.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.util.DefaultIndenter;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.experimental.UtilityClass;

@UtilityClass
public class JsonUtils {

    public static String toJson(Object object) throws JsonProcessingException {
        String json = mapper().writeValueAsString(object);
        return prettyPrint(json);
    }

    public static String prettyPrint(String json) throws JsonProcessingException {
        ObjectMapper mapper = mapper();
        JsonNode node = mapper.readTree(json);
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(node);
    }

    public static ObjectMapper mapper() {
        return new ObjectMapper() {{
            enable(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS);
            enable(SerializationFeature.INDENT_OUTPUT);
            setDefaultPrettyPrinter(new DefaultPrettyPrinter() {
                {
                    _arrayIndenter = new DefaultIndenter("  ", "\n");
                }

                @Override
                public DefaultPrettyPrinter createInstance() {
                    return this;
                }
            });
        }};
    }
}
