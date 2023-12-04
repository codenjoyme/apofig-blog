package com.codenjoy.blog.service.log;

import com.codenjoy.blog.entity.Log;
import com.codenjoy.blog.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.stream.Collectors.joining;

@Component
public class DatabaseLogService implements LogService {

    private static final CharSequence DELIMITER = "\n-------------------\n";

    @Autowired
    private LogRepository logs;

    @Override
    public String getLog(String logFile) {
        // logFile has format
        // logs/{path}/log.log
        // this will get all the parameters
        // TODO make this better
        String[] params = logFile.split("(/|\\\\|\\.log)");
        String path = params[1];
        List<Log> list = logs.findAllByPage(path);

        return list.stream()
                .map(Object::toString)
                .collect(joining(DELIMITER)) + DELIMITER;
    }
}