package com.backend.portalestuderesponda.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(DataExistsException.class)
    public ResponseEntity<ErrorResponse> handleDataExistsException(
            DataExistsException ex, HttpServletRequest request) {
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
        ErrorResponse response = new ErrorResponse();
        response.setStatus(status.value());
        response.setDescription("Dados já existem");
        response.setMessage(ex.getMessage());
        response.setTimestamp(new Date());

        return ResponseEntity.status(status).body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException ex, HttpServletRequest request) {
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;

        Map<String, String> errors = new HashMap<>();

        ex.getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));

        ErrorResponse response = new ErrorResponse();
        response.setStatus(status.value());
        response.setMessage(errors.values().toString());
        response.setTimestamp(new Date());
        response.setDescription(ex.getBindingResult().getAllErrors().get(0).getDefaultMessage());

        return ResponseEntity.status(status).body(response);

    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException ex, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        ErrorResponse response = new ErrorResponse();
        response.setStatus(status.value());
        response.setDescription(ex.getMessage());
        response.setTimestamp(new Date());
        return ResponseEntity.status(status).body(response);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUsernameNotFoundException(UsernameNotFoundException ex, HttpServletRequest request) {
        HttpStatus status = HttpStatus.UNAUTHORIZED;
        ErrorResponse response = new ErrorResponse();
        response.setStatus(status.value());
        response.setDescription("Credenciais incorretas");
        response.setMessage(ex.getMessage());
        response.setTimestamp(new Date());
        return ResponseEntity.status(status).body(response);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErrorResponse> handleAuthenticationException(AuthenticationException ex, HttpServletRequest request) {
        HttpStatus status = HttpStatus.UNAUTHORIZED;
        ErrorResponse response = new ErrorResponse();
        response.setStatus(status.value());
        response.setMessage(ex.getMessage());
        response.setDescription("Credenciais incorretas");
        response.setTimestamp(new Date());
        return ResponseEntity.status(status).body(response);
    }
}
