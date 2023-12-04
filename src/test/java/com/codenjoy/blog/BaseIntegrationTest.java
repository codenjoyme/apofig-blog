package com.codenjoy.blog;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@Import(TestConfig.class)
public abstract class BaseIntegrationTest {

    @Value("${logs.path}")
    private String logsPath;

    public String loadFile(String path) {
        try {
            File file = new File(path);
            System.out.println("Loading file: " + file.getAbsolutePath());
            return FileUtils.readFileToString(file, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    public void deleteLogsDir() {
        try {
            Path path = Paths.get(logsPath);
            Resource resource = new UrlResource(path.toUri());
            FileUtils.deleteDirectory(resource.getFile());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}