package com.codenjoy.blog.enums;

import com.codenjoy.blog.utils.EnumUtils;

import java.util.Optional;

public enum LogLevel {

    ERROR,
    WARN,
    LOG;

    public static Optional<LogLevel> fromString(String value) {
        return EnumUtils.fromString(values(), value);
    }
}