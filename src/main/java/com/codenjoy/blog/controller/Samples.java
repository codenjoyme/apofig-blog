package com.codenjoy.blog.controller;

public class Samples {

    public static final String SAMPLE_PAGE_NAME =
            "2008-06-26_09-20-00_Some-title.md";

    public static final String SAMPLE_PAGES =
            "[\n" +
            "  {\n" +
            "    'fileName': '2008-06-25_15-30-00_hello-world.md',\n" +
            "    'description': 'hello world',\n" +
            "    'settings': {\n" +
            "      'tags': 'hello',\n" +
            "      'time': '2008-06-25 15:30:00'\n" +
            "    }\n" +
            "  },\n" +
            "  {\n" +
            "    'fileName': '2008-06-26_09-20-00_Some-title.md',\n" +
            "    'description': 'Some title',\n" +
            "    'settings': {\n" +
            "      'tags': 'hello, empty',\n" +
            "      'time': '2008-06-26 09:20:00'\n" +
            "    }\n" +
            "  },\n" +
            "  {\n" +
            "    'fileName': '2008-07-15_20-03-00_Untitled.md',\n" +
            "    'description': 'Untitled',\n" +
            "    'settings': {\n" +
            "      'tags': 'empty',\n" +
            "      'time': '2008-07-15 20:03:00'\n" +
            "    }\n" +
            "  }\n" +
            "]";

    public static final String SAMPLE_TAGS =
            "[\n" +
            "  'empty',\n" +
            "  'hello'\n" +
            "]";

    public static final String SAMPLE_PAGE_CONTENT =
            "<h1>Some title</h1>\n" +
            "<p>Text with <strong>bold</strong>, <em>italic</em> and <code>code</code> words.</p>\n" +
            "<p>Some text with <a href='http://example.com'>link</a>.</p>\n" +
            "<p>Maybe some image:\n" +
            "<img src='http://example.com/image.jpg' alt='alt text' title='Title' />.</p>\n" +
            "<p>Code block:</p>\n" +
            "<pre><code class='language-java'>public class Test {\n" +
            "    public static void main(String[] args) {\n" +
            "        System.out.println(&quot;Hello, world!&quot;);\n" +
            "    }\n" +
            "}\n" +
            "</code></pre>\n" +
            "<p>And some list:</p>\n" +
            "<ul>\n" +
            "<li>one</li>\n" +
            "<li>two</li>\n" +
            "<li>three</li>\n" +
            "<li>four\n" +
            "<ul>\n" +
            "<li>four.one</li>\n" +
            "<li>four.two</li>\n" +
            "<li>four.three</li>\n" +
            "<li>four.four</li>\n" +
            "</ul>\n" +
            "</li>\n" +
            "<li>five</li>\n" +
            "</ul>\n" +
            "<pre><code>post:   \n" +
            "  tags: hello, empty\n" +
            "  time: 2008-06-26 09:20:00\n" +
            "</code></pre>\n";

    public static final String SECURED_OPERATION =
            "This is secured operation. You should specify the secret key " +
            "that is specified in the application.properties file.";

    public static final String SECURED_KEY =
            "Secret key for admin operations, which is specified in " +
            "the application.properties file";
}