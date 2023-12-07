package com.codenjoy.blog.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static com.codenjoy.blog.service.ProfileStatus.NO_CACHE;
import static java.nio.charset.StandardCharsets.UTF_8;

@Component
@RequiredArgsConstructor
public class FileService {

    private final ProfileStatus profile;

    private InputStream stream(String filePath) {
        if (profile.isEnabled(NO_CACHE)) {
            try {
                return new FileInputStream("src/main/resources/" + filePath);
            } catch (FileNotFoundException e) {
                // do nothing
            }
        }
        try {
            return new FileInputStream(filePath);
        } catch (FileNotFoundException e) {
            // do nothing
        }
        return getClass().getResourceAsStream("/" + filePath);
    }

    private String load(InputStream stream) throws IOException {
        return new String(stream.readAllBytes(), UTF_8);
    }

    public String loadFile(String filePath) {
        InputStream stream = null;
        try {
            stream = stream(filePath);
            if (stream == null) {
                return null;
            }

            return load(stream);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (stream != null) {
                    stream.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public List<String> files(String directory) {
        return Arrays.asList(Objects.requireNonNull(new File(directory).list()));
    }
}
