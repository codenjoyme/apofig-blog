package com.codenjoy.blog.facade;

import com.codenjoy.blog.dto.PageDTO;
import com.codenjoy.blog.service.MarkdownService;
import com.codenjoy.blog.service.SecretService;
import com.codenjoy.blog.service.log.LogService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class PageFacade {

    private final LogService logs;
    private final MarkdownService markdown;
    private final SecretService secret;

    public String content(String contextPath, String path) {
        return markdown.load(contextPath, path);
    }

    public List<PageDTO> pages() {
        // TODO implement me
        return List.of(PageDTO.builder()
                .path("page.md")
                .description("First page")
                .build());
    }

    public void load(String secret) {
        this.secret.validate(secret);

        // TODO implement me
    }
}