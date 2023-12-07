package com.codenjoy.blog.service;

import com.codenjoy.blog.converter.YamlConverter;
import com.codenjoy.blog.dto.PageDTO;
import com.codenjoy.blog.dto.PageSettings;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.substringAfterLast;

@Component
@RequiredArgsConstructor
public class PageService {

    private final YamlConverter yaml;
    private final FileService files;

    public PageSettings loadSettings(String filePath) {
        String content = files.loadFile(filePath);

        // file contains block inside
//        ```
//        post:
//          tags: hello, empty
//          time: 2008-06-26 09:20:00
//        ```
        // I want to get this block without 'post:' by using regexp
        // there can be other blocks like '```' in the file,
        // so I need to get the block that starts with 'post:' and ends with '```'

        Pattern pattern = Pattern.compile("post:(.*?)```", Pattern.DOTALL);
        String settings = pattern.matcher(content).results()
                .map(matchResult -> matchResult.group(1))
                .findFirst()
                .orElse(null);


        if (!isBlank(settings)) {
            return yaml.from(settings);
        }
        return PageSettings.builder().build();
    }

    private String description(String file) {
        return substringAfterLast(file, "_")
                .replaceAll("-", " ")
                .replaceAll("\\.md", "");
    }

    public Stream<PageDTO> pages(String directory) {
        return files.files(directory).stream()
                .filter(file -> file.endsWith(".md"))
                .map(file -> PageDTO.builder()
                        .fileName(file)
                        .description(description(file))
                        .settings(loadSettings(directory + "/" + file))
                        .build())
                .sorted(Comparator.comparing(PageDTO::time));
    }
}