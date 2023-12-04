package com.codenjoy.blog.exception;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Slf4j
@ControllerAdvice
public class GlobalControllerExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception exception) {
        log.error("Exception occurred", exception);
        List<Throwable> causes = getCauses(exception);
        List<String> errors = getMessages(causes);
        return getErrorResponse(errors, INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationErrors(MethodArgumentNotValidException exception) {
        List<String> errors = exception.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + " " + error.getDefaultMessage())
                .collect(toList());
        return getErrorResponse(errors, BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleEnumErrors(HttpMessageNotReadableException exception) {
        if (isEnumError(exception)) {
            InvalidFormatException cause = (InvalidFormatException) exception.getCause();
            String message = getEnumMessage(cause);
            return getErrorResponse(message, BAD_REQUEST);

        }
        return getErrorResponse(exception.getMessage(), BAD_REQUEST);
    }

    private List<String> getMessages(List<Throwable> causes) {
        return causes.stream()
                .map(this::getMessage)
                .collect(toList());
    }

    private List<Throwable> getCauses(Throwable exception) {
        List<Throwable> result = new LinkedList<>();
        result.add(exception);
        if (exception.getCause() != null) {
            result.addAll(getCauses(exception.getCause()));
        }
        return result;

    }

    private String getMessage(Throwable exception) {
        return exception.getClass().getSimpleName() + ": " + exception.getMessage();
    }

    private ResponseEntity<ErrorResponse> getErrorResponse(String error, HttpStatus status) {
        return getErrorResponse(List.of(error), status);
    }

    private ResponseEntity<ErrorResponse> getErrorResponse(List<String> errors, HttpStatus status) {
        return ResponseEntity
                .status(status)
                .headers(new HttpHeaders())
                .body(getErrorResponse(errors));
    }

    private ErrorResponse getErrorResponse(List<String> errors) {
        return ErrorResponse.builder()
                .errors(errors)
                .build();
    }

    private boolean isEnumError(HttpMessageNotReadableException exception) {
        if (!(exception.getCause() instanceof InvalidFormatException)) {
            return false;
        }

        InvalidFormatException cause = (InvalidFormatException) exception.getCause();
        return cause.getTargetType() != null && cause.getTargetType().isEnum();
    }

    private String getEnumMessage(InvalidFormatException exception) {
        String available = Arrays.toString(exception.getTargetType().getEnumConstants());
        String fieldName = Optional.ofNullable(CollectionUtils.lastElement(exception.getPath()))
                .map(JsonMappingException.Reference::getFieldName)
                .orElse(StringUtils.EMPTY);
        return String.format("Invalid enum value: '%s' for the field: '%s'. The value must be one of: %s.",
                exception.getValue(), fieldName, available);
    }
}