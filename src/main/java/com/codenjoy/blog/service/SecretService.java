package com.codenjoy.blog.service;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SecretService {

    @Getter
    @Value("${delete.secret}")
    private String secret;

    public void validate(String secret) {
        if (!this.secret.equals(secret)) {
            throw new IllegalStateException("Permission denied");
        }
    }
}
