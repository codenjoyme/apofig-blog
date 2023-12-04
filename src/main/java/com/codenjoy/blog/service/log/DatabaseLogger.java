package com.codenjoy.blog.service.log;

import com.codenjoy.blog.entity.Log;
import com.codenjoy.blog.enums.LogLevel;
import com.codenjoy.blog.enums.LogType;
import com.codenjoy.blog.repository.LogRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;

import static java.time.format.DateTimeFormatter.ofPattern;
import static org.apache.commons.lang3.StringUtils.isBlank;


@Slf4j
@Component
@Scope(value = WebApplicationContext.SCOPE_REQUEST,
        proxyMode = ScopedProxyMode.TARGET_CLASS)
public class DatabaseLogger implements ManageLogger {

    @Autowired
    private LogRepository logs;

    @Value("${logs.path}")
    private String root;

    private LogType type;
    private String path;

    @Override
    public void init(LogType type, String path) {
        this.type = type;
        this.path = path;

        log("Page path: " + path);
    }

    @Override
    public void log(String message, Object... args) {
        message = String.format(message, args);

        log.info(message);
        save(message, LogLevel.LOG);
    }

    @Override
    public void error(String message, Object... args) {
        message = String.format(message, args);

        log.error(message);
        save(message, LogLevel.ERROR);
    }

    private void save(String message, LogLevel level) {
        logs.save(Log.builder()
                .page(path)
                .type(type)
                .level(level)
                .time(now())
                .phase(null) // TODO implement phases
                .message(message)
                .build());
    }

    @Override
    public String getFilePath() {
        return getLogPath() + "log.log";
    }

    @Override
    public void clean(String path) {
        logs.deleteAllByPage(path);
    }

    private String getLogPath() {
        if (isBlank(path)) {
            log.error("Page path is blank logging to logs path");
            return root + type.getFolder();
        }
        String result = root + path + "/" + type.getFolder();
        return result;
    }

    private String now() {
        return LocalDateTime.now()
                .format(ofPattern("uuuu-MM-dd'T'HH-mm-ss.SSSSSSSSS"));
    }
}