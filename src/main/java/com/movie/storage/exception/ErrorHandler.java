package com.movie.storage.exception;

import com.movie.storage.app.model.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler {
    private static final int DEFAULT_CODE = 10;

    @ExceptionHandler(value = Exception.class)
    protected ResponseEntity<Object> handleGlobalError(Exception ex) {
        log.error("Critical error occurs: {} - {}", ex.getClass().getName(), ex.getMessage());

        return new ResponseEntity<>(new ErrorResponse().code(DEFAULT_CODE).message("Global service malfunction"),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = FilmValidationException.class)
    protected ResponseEntity<Object> handleValidationException(FilmValidationException ex) {
        log.error("Validation error occurs: {}", ex.getMessage());

        return new ResponseEntity<>(new ErrorResponse().code(DEFAULT_CODE).message(ex.getMessage()),
                HttpStatus.BAD_REQUEST);
    }
}
