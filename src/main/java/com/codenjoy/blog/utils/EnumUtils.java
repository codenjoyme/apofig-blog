package com.codenjoy.blog.utils;

import lombok.experimental.UtilityClass;

import java.util.Arrays;
import java.util.Optional;

@UtilityClass
public class EnumUtils {

    public static <E extends Enum> Optional<E> fromString(E[] values, String value) {
        return Arrays.stream(values)
                .filter(type -> type.name().equalsIgnoreCase(value))
                .findFirst();
    }
}
