package com.codenjoy.blog.service;

import com.codenjoy.blog.dto.PageSettings;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.ast.Document;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static com.codenjoy.blog.service.ProfileStatus.TEST;
import static java.util.stream.Collectors.joining;

@Slf4j
@Component
@RequiredArgsConstructor
public class MarkdownService {

    public static final String CONTEXT_PATH = "${contextPath}";

    private final FileService files;
    private final ProfileStatus profile;
    private final PageService pages;

    @Value("${server.servlet.context-path}")
    private String contextPath;

    public String load(String filePath) {
        if (!filePath.endsWith(".md")
                || filePath.contains("..")
                || (!filePath.startsWith("data") && !profile.isEnabled(TEST)))
        {
            throw new IllegalArgumentException("Invalid file name: " + filePath);
        }

        String markdown = files.loadFile(filePath);
        PageSettings settings = pages.loadSettingsByContent(markdown);
        return render(markdown, settings);
    }

    private String addContext(String markdown, String contextPath) {
        return markdown.replace(CONTEXT_PATH, contextPath);
    }

    private String render(String markdown, PageSettings settings) {
        if (markdown == null) {
            return null;
        }
        if (settings.present()) {
            markdown = new StringBuilder(markdown)
                    .delete(settings.getPosition().getLeft(),
                            settings.getPosition().getRight())
                    .toString();

            markdown = markdown +
                    makeBlockQuote(
                            buildTags(settings.tags()),
                            buildTime(settings.getTime()),
                            buildSource(settings.sources()));
        }

        markdown = addContext(markdown, contextPath);

        return render(markdown);
    }

    private String makeBlockQuote(String... lines) {
        return Arrays.stream(lines)
                .filter(StringUtils::isNotBlank)
                .map(line -> String.format("> %s<br>\n", line))
                .collect(joining(""));
    }

    private String render(String markdown) {
        Parser parser = Parser.builder().build();
        Document document = parser.parse(markdown);
        HtmlRenderer renderer = HtmlRenderer.builder().build();
        return renderer.render(document);
    }

    private String buildSource(List<String> sources) {
        AtomicInteger counter = new AtomicInteger(0);
        return sources.isEmpty()
                ? StringUtils.EMPTY
                : String.format("**Source:** %s",
                    sources.stream()
                            .map(source -> String.format("[Link%s](%s) [(Web archive)](%s)",
                                    sources.size() > 1 ? counter.incrementAndGet() : StringUtils.EMPTY,
                                    link(source),
                                    link("https://web.archive.org/*/" + source)))
                            .collect(joining(", ")));

    }

    private String buildTime(String time) {
        return time == null
                ? StringUtils.EMPTY
                : String.format("**Time:** %s", time);
    }

    private String buildTags(List<String> tags) {
        return tags.isEmpty()
                ? StringUtils.EMPTY
                : String.format("**Tags:** %s",
                    tags.stream()
                            .map(tag -> String.format("[%s](%s/ui/pages?tag=%s)",
                                    tag, CONTEXT_PATH, link(tag)))
                            .collect(joining(", ")));
    }

    private String link(String href) {
        return href.replace(" ", "%20");
    }
}