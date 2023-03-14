package com.hogarcontrols.hogarcloud.admin.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<String> exception403Handler(AccessDeniedException e) {
        log.error("Token is valid but Access Denied", e);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("AccessDenied");
    }
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<String> exceptionHandler(Exception e) {
        log.error("Exception", e);
        return ResponseEntity.internalServerError().body("Sorry");
    }
}
