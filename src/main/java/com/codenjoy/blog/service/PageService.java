package com.codenjoy.blog.service;

import com.codenjoy.blog.converter.YamlConverter;
import com.codenjoy.blog.dto.PageDTO;
import com.codenjoy.blog.dto.PageSettings;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.substringAfterLast;

@Component
@RequiredArgsConstructor
public class PageService {

    private final YamlConverter yaml;
    private final FileService files;

    public PageSettings loadSettings(String filePath) {
        return loadSettingsByContent(files.loadFile(filePath));
    }

    public PageSettings loadSettingsByContent(String content) {
        // file contains block inside
//        ```
//        post:
//          tags: hello, empty
//          time: 2008-06-26 09:20:00
//        ```
        // I want to get this block without 'post:' by using regexp
        // there can be other blocks like '```' in the file,
        // so I need to get the block that starts with 'post:' and ends with '```'

        Pattern pattern = Pattern.compile("```\\s*\\npost:(.*?)\\n```", Pattern.DOTALL);
        PageSettings settings = pattern.matcher(content).results()
                .findFirst()
                .map(this::extractSettings)
                .orElseGet(() -> PageSettings.builder().build());

        return settings;
    }

    private PageSettings extractSettings(MatchResult match) {
        String settings = match.group(1);
        int start = match.start();
        int end = match.end();

        PageSettings result = yaml.from(settings);
        result.setPosition(Pair.of(start, end));

        return result;
    }

    private String description(String file) {
        return substringAfterLast(file, "_")
                .replaceAll("-", " ")
                .replaceAll("\\.md", "");
    }

    public Stream<PageDTO> pages(String directory) {
        return files.files(directory).stream()
                .filter(file -> file.endsWith(".md"))
                .filter(file -> !file.startsWith("_")) // _readme.md
                .map(file -> PageDTO.builder()
                        .fileName(file)
                        .description(description(file))
                        .settings(loadSettings(directory + "/" + file))
                        .build())
                .sorted(Comparator.comparing(PageDTO::time));
    }

    public List<String> tags(String directory) {
        return pages(directory)
                .filter(page -> page.getSettings().present())
                .flatMap(page -> page.getSettings().tags().stream())
                .distinct()
                .sorted()
                .collect(toList());
    }
}