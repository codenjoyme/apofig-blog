package com.codenjoy.blog.service;

import com.codenjoy.blog.converter.YamlConverter;
import com.codenjoy.blog.dto.PageDTO;
import com.codenjoy.blog.dto.PageSettings;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.apache.commons.lang3.StringUtils.*;

@Component
@RequiredArgsConstructor
public class PageService {

    private final YamlConverter yaml;
    private final FileService files;

    public PageSettings loadSettings(String filePath) {
        String content = files.loadFile(filePath);
        String settings = substringAfter(substringBetween(content, "```", "```"), "post:");
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

    public List<PageDTO> pages(String directory) {
        return files.files(directory).stream()
                .filter(file -> file.endsWith(".md"))
                .map(file -> PageDTO.builder()
                        .fileName(file)
                        .description(description(file))
                        .settings(loadSettings(directory + "/" + file))
                        .build())
                .collect(toList());
    }
}
