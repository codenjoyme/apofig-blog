package com.codenjoy.blog.exception;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ErrorResponse {

    private List<String> errors;
}