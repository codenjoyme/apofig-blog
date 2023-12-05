package com.codenjoy.blog.facade;

import com.codenjoy.blog.dto.PageDTO;
import com.codenjoy.blog.service.FileService;
import com.codenjoy.blog.service.GitService;
import com.codenjoy.blog.service.MarkdownService;
import com.codenjoy.blog.service.SecretService;
import com.codenjoy.blog.service.log.LogService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
@AllArgsConstructor
public class PageFacade {

    private static final String REPO = "https://github.com/codenjoyme/apofig.com.git";
    private static final String DIRECTORY = "data/apofig.com";

    private final LogService logs;
    private final MarkdownService markdown;
    private final SecretService secret;
    private final GitService git;
    private final FileService files;

    public String content(String contextPath, String path) {
        return markdown.load(contextPath, DIRECTORY + "/" + path);
    }

    public List<PageDTO> pages() {
        return files.files(DIRECTORY).stream()
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
        return file.replaceAll("(\\d{4})-(\\d{2})-(\\d{2})_(\\d{2})-(\\d{2})-\\d{2}_", "[$1_$2_$3 $2:$3] ")
                .replaceAll("-", " ")
                .replaceAll("_", "-")
                .replaceAll("\\.md", "");
    }

    public void load(String secret) {
        this.secret.validate(secret);

        File dir = new File(DIRECTORY);
        if (dir.exists()) {
            git.pull(dir)
                    .orElseThrow(() -> new IllegalArgumentException(
                            "Can not pull repository: " + REPO));
        } else {
            git.clone(REPO, dir)
                    .orElseThrow(() -> new IllegalArgumentException(
                            "Can not clone repository: " + REPO));
        }
    }
}