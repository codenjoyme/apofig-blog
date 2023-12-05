package com.codenjoy.blog.facade;

import com.codenjoy.blog.dto.PageDTO;
import com.codenjoy.blog.service.FileService;
import com.codenjoy.blog.service.GitService;
import com.codenjoy.blog.service.MarkdownService;
import com.codenjoy.blog.service.SecretService;
import com.codenjoy.blog.service.log.LogService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.util.List;

import static java.util.stream.Collectors.toList;
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

    @PostConstruct
    public void init() {
        directory = "data/" + substringAfterLast(substringBefore(repo, ".git"), "/");
    }

    public String content(String contextPath, String path) {
        return markdown.load(contextPath, directory + "/" + path);
    }

    public List<PageDTO> pages() {
        return files.files(directory).stream()
                .filter(file -> file.endsWith(".md"))
                .map(file -> PageDTO.builder()
                        .path(file)
                        .description(description(file))
                        .build())
                .collect(toList());
    }

    /**
     * file name is '2008-06-26_09-20-00_Be-careful-at-the-look-of-a-builder-who-just-sits-there.md'
     * I want to get '[2008-06-26 09:20] Be careful at the look of a builder who just sits there'
     * TODO Please improve
     */
    private static String description(String file) {
        return file.replaceAll("(\\d{4})-(\\d{2})-(\\d{2})_(\\d{2})-(\\d{2})-\\d{2}_", "[$1_$2_$3 $4:$5] ")
                .replaceAll("-", " ")
                .replaceAll("_", "-")
                .replaceAll("\\.md", "");
    }

    public void load(String secret) {
        this.secret.validate(secret);

        File dir = new File(directory);
        if (dir.exists()) {
            git.pull(dir)
                    .orElseThrow(() -> new IllegalArgumentException(
                            "Can not pull repository: " + repo));
        } else {
            git.clone(repo, dir)
                    .orElseThrow(() -> new IllegalArgumentException(
                            "Can not clone repository: " + repo));
        }
    }
}