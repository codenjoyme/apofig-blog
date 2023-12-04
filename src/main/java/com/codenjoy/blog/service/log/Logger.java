package com.codenjoy.blog.service.log;

public interface Logger {

    void log(String message, Object... args);

    default void error(String message, Object... args) {
        log("ERROR: " + message, args);
    }
}