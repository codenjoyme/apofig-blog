package com.codenjoy.blog.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static com.codenjoy.blog.service.ProfileStatus.NO_CACHE;
import static java.nio.charset.StandardCharsets.UTF_8;

@Component
@RequiredArgsConstructor
public class FileService {

    public static final String SRC_RESOURCES = "src/main/resources/";

    private final ProfileStatus profile;

    public String loadFile(String filePath) {
        if (profile.isEnabled(NO_CACHE)) {
            try {
                Path path = Paths.get(SRC_RESOURCES + filePath);
                return Files.readString(path, UTF_8);
            } catch (IOException e) {
                // do nothing
            }
        }

        try {
            Path path = Paths.get(filePath);
            return Files.readString(path, UTF_8);
        } catch (IOException e) {
            // do nothing
        }
        throw new IllegalArgumentException("Invalid file name: " + filePath);
    }

    public List<String> files(String directory) {
        return Arrays.asList(Objects.requireNonNull(new File(directory).list()));
    }
}
