package com.codenjoy.blog.controller;

import com.codenjoy.blog.BaseIntegrationTest;
import com.codenjoy.blog.service.SecretService;
import com.codenjoy.blog.utils.JsonUtils;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

public abstract class BaseControllerTest extends BaseIntegrationTest {

    protected MockMvc mvc;

    @Value("${server.servlet.context-path}")
    protected String contextPath;

    @Autowired
    protected SecretService secret;

    @Autowired
    protected WebApplicationContext context;

    @BeforeEach
    public void setup() {
        // given
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @AfterEach
    public void after() {
    }

    public String secret(String url) {
        return String.format("%s?secret=%s", url, secret.getSecret());
    }

    @SneakyThrows
    public JsonNode jsonNode(String json) {
        return JsonUtils.mapper().readTree(json);
    }

    public String fix(String json) {
        return json
                .replace("\" :", "\":")
                .replace("\"", "'")
                .replace("\\n", "\n")
                .replace(System.lineSeparator(), "\n");
    }
}
