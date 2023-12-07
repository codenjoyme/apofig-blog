package com.codenjoy.blog.controller;

public class Samples {

    public static final String SAMPLE_PAGE_NAME = "2008-06-25_15-30-00_hello-world.md";

    public static final String SAMPLE_PAGES =
            "[\n" +
            "  {\n" +
            "    'fileName': '2008-06-25_15-30-00_hello-world.md',\n" +
            "    'description': 'hello world',\n" +
            "    'settings': {\n" +
            "      'tags': '',\n" +
            "      'time': '2008-06-25 15:30:00'\n" +
            "    }\n" +
            "  },\n" +
            "  {\n" +
            "    'fileName': '2008-07-15_20-03-00_Untitled.md',\n" +
            "    'description': 'Untitled',\n" +
            "    'settings': {\n" +
            "      'tags': '',\n" +
            "      'time': '2008-07-15 20:03:00'\n" +
            "    }\n" +
            "  }\n" +
            "]";

    public static final String SAMPLE_PAGE_CONTENT =
            "<h1>Hello world!</h1>\n" +
            "<p>Привет мир! Вот он я - контент. Я попробую тут наполняться, если понравится -\n" +
            "останусь, иначе перееду. Мой генератор любит много писать и часть меня находится\n" +
            "на ЖЖ. Время покажет...</p>\n" +
            "<pre><code>post:   \n" +
            "  tags: \n" +
            "  time: 2008-06-25 15:30:00\n" +
            "</code></pre>\n";

    public static final String SECURED_OPERATION =
            "This is secured operation. You should specify the secret key " +
            "that is specified in the application.properties file.";

    public static final String SECURED_KEY =
            "Secret key for admin operations, which is specified in " +
            "the application.properties file";
}