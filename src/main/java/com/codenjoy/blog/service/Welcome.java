package com.codenjoy.blog.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.awt.*;

@Slf4j
@Component
@RequiredArgsConstructor
public class Welcome {

    @PostConstruct
    public void helloWorld() {
        log.info("Staring...");
        Toolkit.getDefaultToolkit().beep();
    }
}