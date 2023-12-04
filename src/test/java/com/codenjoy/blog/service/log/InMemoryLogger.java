package com.codenjoy.blog.service.log;

public class InMemoryLogger implements Logger {

    private StringBuilder buffer;

    public InMemoryLogger() {
        clear();
    }

    public void clear() {
        buffer = new StringBuilder();
    }

    @Override
    public void log(String message, Object... args) {
        message = String.format(message, args);

        buffer.append(message).append("\n");
    }

    @Override
    public String toString() {
        return buffer.toString();
    }
}