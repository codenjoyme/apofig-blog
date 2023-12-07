package com.codenjoy.blog.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.apache.commons.lang3.StringUtils.isBlank;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PageSettings {

    @JsonProperty(index = 10)
    private String tags;

    @JsonProperty(index = 20)
    private String time;

    public List<String> tags() {
        return isBlank(tags)
                ? List.of()
                : Arrays.stream(tags.split(","))
                    .map(String::trim)
                    .filter(StringUtils::isNotBlank)
                    .collect(toList());
    }
}