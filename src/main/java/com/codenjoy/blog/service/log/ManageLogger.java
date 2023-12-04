package com.codenjoy.blog.service.log;

import com.codenjoy.blog.enums.LogType;

public interface ManageLogger extends Logger {

    void init(LogType type, String path);

    String getFilePath();

    void clean(String path);
}