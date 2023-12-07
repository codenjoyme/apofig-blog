package com.codenjoy.blog.service;

import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@Controller
@RequiredArgsConstructor
public class ProfileStatus {

    public static final String NO_CACHE = "nocache";
    public static final String LOCAL = "local";
    public static final String TEST = "test";

    private final Environment environment;

    public Boolean isEnabled(String profile) {
        return Stream.of(environment.getActiveProfiles())
                .anyMatch(profile::equalsIgnoreCase);
    }

    public List<String> getActiveProfiles() {
        return Arrays.asList(environment.getActiveProfiles());
    }
}