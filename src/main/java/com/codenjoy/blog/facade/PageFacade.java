package com.codenjoy.blog.facade;

import com.codenjoy.blog.dto.PageDTO;
import com.codenjoy.blog.service.*;
import com.codenjoy.blog.service.log.LogService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.substringAfterLast;
import static org.apache.commons.lang3.StringUtils.substringBefore;

@Component
@RequiredArgsConstructor
public class PageFacade {

    @Value("${git.repo}")
    private String repo;
    private String directory;

    private final LogService logs;
    private final MarkdownService markdown;
    private final SecretService secret;
    private final GitService git;
    private final FileService files;
    private final PageService pages;

    @PostConstruct
    public void init() {
        directory = "data/" + substringAfterLast(substringBefore(repo, ".git"), "/");
    }

    public String content(String fileName) {
        return markdown.load(directory + "/" + fileName);
    }

    public List<PageDTO> pages() {
        return pages.pages(directory);
    }

    public void load(String secret) {
        this.secret.validate(secret);

        git.pullOrClone(repo, directory);
    }
}