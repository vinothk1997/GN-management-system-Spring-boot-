package com.GNManagementSystem.GnManagementSystem.exception;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@Getter
@Slf4j
public class ServiceException extends RuntimeException {
    private final String headerMessage;
    private final HttpStatus httpStatus;

    public ServiceException(String message, String headerMessage, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
        this.headerMessage = headerMessage;
    }

    @ExceptionHandler(value = {Exception.class})
    protected ResponseEntity<Object> handleException(Exception ex) {
        log.error("Internal server error: ", ex);
        ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal Server Error", List.of("Internal Service Exception"));
        return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value ={ServiceException.class})
    protected ResponseEntity<Object> handleServiceException(ServiceException ex) {
        log.error("Internal service error: ", ex);
        ApiError apiError = new ApiError(ex.getHttpStatus().value(), ex.getHeaderMessage(), List.of(ex.getMessage()));
        return new ResponseEntity<>(apiError, ex.getHttpStatus());
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ApiError {
        private int status;
        private String message;
        private List<String> errors;
    }

}
