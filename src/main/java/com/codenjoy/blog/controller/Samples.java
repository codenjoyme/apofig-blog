package com.codenjoy.blog.controller;

public class Samples {

    public static final String SAMPLE_PAGE_NAME =
            "2008-06-26_09-20-00_Some-title.md";

    public static final String SAMPLE_PAGES =
            "[\n" +
            "  {\n" +
            "    'fileName': '2008-08-08_21-13-00_Without-settings.md',\n" +
            "    'description': 'Without settings',\n" +
            "    'settings': { }\n" +
            "  },\n" +
            "  {\n" +
            "    'fileName': '2008-09-30_12-14-13_Multi-sources.md',\n" +
            "    'description': 'Multi sources',\n" +
            "    'settings': {\n" +
            "      'source': 'https://site1.com\n" +
            "https://site2.com\n" +
            "https://site3.com',\n" +
            "      'position': {\n" +
            "        '16': 118\n" +
            "      }\n" +
            "    }\n" +
            "  },\n" +
            "  {\n" +
            "    'fileName': '2008-06-25_15-30-00_hello-world.md',\n" +
            "    'description': 'hello world',\n" +
            "    'settings': {\n" +
            "      'tags': 'hello',\n" +
            "      'time': '2008-06-25 15:30:00',\n" +
            "      'source': 'https://site.com',\n" +
            "      'position': {\n" +
            "        '209': 299\n" +
            "      }\n" +
            "    }\n" +
            "  },\n" +
            "  {\n" +
            "    'fileName': '2008-06-26_09-20-00_Some-title.md',\n" +
            "    'description': 'Some title',\n" +
            "    'settings': {\n" +
            "      'tags': 'hello, empty, with space',\n" +
            "      'time': '2008-06-26 09:20:00',\n" +
            "      'position': {\n" +
            "        '456': 537\n" +
            "      }\n" +
            "    }\n" +
            "  },\n" +
            "  {\n" +
            "    'fileName': '2008-07-15_20-03-00_Untitled.md',\n" +
            "    'description': 'Untitled',\n" +
            "    'settings': {\n" +
            "      'tags': 'empty',\n" +
            "      'time': '2008-07-15 20:03:00',\n" +
            "      'position': {\n" +
            "        '0': 62\n" +
            "      }\n" +
            "    }\n" +
            "  }\n" +
            "]";

    public static final String SAMPLE_TAGS =
            "[\n" +
            "  'empty',\n" +
            "  'hello',\n" +
            "  'with space'\n" +
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
            "<blockquote>\n" +
            "<p><strong>Tags:</strong> <a href='/blog/ui/pages?tag=hello'>hello</a>, <a href='/blog/ui/pages?tag=empty'>empty</a>, <a href='/blog/ui/pages?tag=with%20space'>with space</a><br>\n" +
            "<strong>Time:</strong> 2008-06-26 09:20:00<br></p>\n" +
            "</blockquote>\n";

    public static final String SECURED_OPERATION =
            "This is secured operation. You should specify the secret key " +
            "that is specified in the application.properties file.";

    public static final String SECURED_KEY =
            "Secret key for admin operations, which is specified in " +
            "the application.properties file";
}