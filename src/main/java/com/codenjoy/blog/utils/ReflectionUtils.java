package com.codenjoy.blog.utils;

import lombok.experimental.UtilityClass;
import org.reflections.Reflections;
import org.reflections.util.ConfigurationBuilder;

import java.lang.reflect.Modifier;
import java.util.function.Consumer;

@UtilityClass
public class ReflectionUtils {

    public static <T> void scanPackage(Class<?> baseClass, Consumer<Class<?>> consumer) {
        Reflections reflections = new Reflections(new ConfigurationBuilder().forPackages(baseClass.getPackage().getName()));
        reflections.getSubTypesOf(baseClass).forEach(clazz -> {
            if (clazz == null
                    || clazz.isInterface()
                    || Modifier.isAbstract(clazz.getModifiers())) {
                return;
            }
            consumer.accept(clazz);
        });
    }

}

