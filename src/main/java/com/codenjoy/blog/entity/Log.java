package com.codenjoy.blog.entity;

import com.codenjoy.blog.enums.LogLevel;
import com.codenjoy.blog.enums.LogType;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "log")
public class Log {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "page")
    private String page;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private LogType type;

    @Enumerated(EnumType.STRING)
    @Column(name = "level")
    private LogLevel level;

    @Column(name = "time", columnDefinition = "TEXT")
    private String time;

    @Column(name = "phase", columnDefinition = "TEXT")
    private String phase;

    @Column(name = "message", columnDefinition = "TEXT")
    private String message;

    public String toString() {
        if (level == LogLevel.ERROR) {
            return String.format("ERROR: %s", message);
        } else {
            return message;
        }
    }
}