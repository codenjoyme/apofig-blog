package com.codenjoy.blog.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import static com.vladsch.flexmark.util.misc.Utils.isBlank;

@Data
@Builder
@Schema(description = "The Page is represented by its path and description.")
public class PageDTO {

    @Schema(description = "File name")
    private String fileName;

    @Schema(description = "Description of the Page")
    private String description;

    @Schema(description = "Page settings")
    private PageSettings settings;

    public String time() {
        return settings.getTime();
    }

    public boolean hasTag(String tag) {
        if (isBlank(tag)) {
            return true;
        }
        return settings.tags().contains(tag);
    }
}