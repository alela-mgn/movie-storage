package com.movie.storage.exception;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import com.fasterxml.jackson.databind.exc.ValueInstantiationException;
import com.movie.storage.app.model.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;

import static java.lang.String.format;

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

    @ExceptionHandler(value = ConstraintViolationException.class)
    protected ResponseEntity<Object> handleValidationException(ConstraintViolationException ex) {
        log.error("Validation error occurs: {}", ex.getMessage());
        String message = resolveMessage(ex.getMessage());

        return new ResponseEntity<>(new ErrorResponse().code(DEFAULT_CODE).message(message),
                HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return handleHttpMessageNotReadableException(ex);
    }

    private String resolveMessage(String original) {
        if (original == null) {
            return "";
        }

        if (original.contains("createMultipleFilms.")) {
            return original.replace("createMultipleFilms.", "");
        }

        return original.substring(original.indexOf("Invalid input:"));
    }

    private ResponseEntity<Object> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        log.error("Swagger scheme validation  error occurs: {}", ex.getMessage());

        String message = "Invalid input: incorrect request";
        if (ex.getCause() == null) {
            return new ResponseEntity<>(new ErrorResponse().code(DEFAULT_CODE).message(message),
                    HttpStatus.BAD_REQUEST);
        }

        if (ex.getCause() instanceof ValueInstantiationException && ex.getCause().getCause() != null) {
            message = "Invalid input: " + ex.getCause().getCause().getMessage();
        } else if (ex.getCause() instanceof UnrecognizedPropertyException) {
            message = format("Invalid input: unrecognized field '%s'", ((UnrecognizedPropertyException) ex.getCause()).getPropertyName());
        } else if (ex.getCause() instanceof InvalidFormatException) {
            message = "Invalid input: please provide ReleaseDate in correct format YYYY-MM-DD (e.g. 2020-12-24)";
        }

        return new ResponseEntity<>(new ErrorResponse().code(DEFAULT_CODE).message(message),
                HttpStatus.BAD_REQUEST);
    }
}
