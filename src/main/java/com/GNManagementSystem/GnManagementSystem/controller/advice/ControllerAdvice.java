package com.GNManagementSystem.GnManagementSystem.controller.advice;

import com.GNManagementSystem.GnManagementSystem.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class ControllerAdvice {
    @ExceptionHandler(value = {Exception.class})
    protected ResponseEntity<Object> handleException(Exception ex) {
        log.error("Internal server error: ", ex);
        ServiceException.ApiError apiError = new ServiceException.ApiError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal Server Error", List.of("Internal Service Exception"));
        return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value ={ServiceException.class})
    protected ResponseEntity<Object> handleServiceException(ServiceException ex) {
        log.error("Internal service error: ", ex);
        ServiceException.ApiError apiError = new ServiceException.ApiError(ex.getHttpStatus().value(), ex.getHeaderMessage(), List.of(ex.getMessage()));
        return new ResponseEntity<>(apiError, ex.getHttpStatus());
    }

    @ExceptionHandler(OAuth2AuthenticationException.class)
    public ResponseEntity<String> handleOAuth2Exception(OAuth2AuthenticationException ex) {
        log.error("OAuth2 authentication failed: {}", ex.getError().getDescription());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("OAuth2 authentication failed: " + ex.getMessage());
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<String> handleAccessDeniedException(AccessDeniedException ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You do not have access to this resource.");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );
        return ResponseEntity.badRequest().body(errors);
    }
}
