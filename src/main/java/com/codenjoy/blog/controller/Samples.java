package com.codenjoy.blog.controller;

public class Samples {

    public static final String SAMPLE_PAGE_PATH = "page.md";

    public static final String SAMPLE_PAGES =
            "[\n" +
            "  {\n" +
            "    \"path\": \"page.md\",\n" +
            "    \"description\": \"First page\"\n" +
            "  }\n" +
            "]";

    public static final String SAMPLE_PAGE_CONTENT =
            "<h1>Hello World!</h1>\n" +
            "<p>Hello World!</p>\n";

    public static final String SECURED_OPERATION =
            "This is secured operation. You should specify the secret key " +
            "that is specified in the application.properties file.";

    public static final String SECURED_KEY =
            "Secret key for admin operations, which is specified in " +
            "the application.properties file";
}