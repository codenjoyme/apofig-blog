package com.codenjoy.blog.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum LogType {

    DELETE(""),
    CHAT("chat/"),
    TRAIN("train/");

    private final String folder;
}