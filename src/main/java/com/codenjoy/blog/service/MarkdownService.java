package com.codenjoy.blog.service;

import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.ast.Document;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class MarkdownService {

    private final FileService files;

    public String load(String contextPath, String file) {
        if (!file.endsWith(".md")
                || file.contains("..")
                || !file.startsWith("data"))
        {
            throw new IllegalArgumentException("Invalid file name: " + file);
        }

        String markdown = addContext(files.loadFile(file), contextPath);
        return render(markdown);
    }

    private String addContext(String markdown, String contextPath) {
        return markdown.replace("${contextPath}", contextPath);
    }

    private String render(String markdown) {
        if (markdown == null) {
            return null;
        }
        Parser parser = Parser.builder().build();
        Document document = parser.parse(markdown);
        HtmlRenderer renderer = HtmlRenderer.builder().build();
        return renderer.render(document);
    }
}