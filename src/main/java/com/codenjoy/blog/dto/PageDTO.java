package com.codenjoy.blog.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(description = "The Page is represented by its path and description.")
public class PageDTO {

    @Schema(description = "File name")
    private String fileName;

    @Schema(description = "Description of the Page")
    private String description;
}