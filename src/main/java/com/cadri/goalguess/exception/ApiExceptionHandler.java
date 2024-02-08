package com.cadri.goalguess.exception;

import com.cadri.goalguess.ApiError;
import com.cadri.goalguess.dto.ErrorDTO;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(RestException.class)
    public ResponseEntity<ErrorDTO> duplicateResource (RestException e, WebRequest request) {
        return ResponseEntity.status(e.getStatus()).body(new ErrorDTO(e.getDescription(), e.getReasons()));
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<String> reasons = new ArrayList<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            reasons.add(String.format("%s - %s", error.getField(), error.getDefaultMessage()));
        }
        return ResponseEntity.status(ApiError.VALIDATION_ERROR.getHttpStatus())
                .body(new ErrorDTO(ApiError.VALIDATION_ERROR.getMessage(), reasons));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex,
                                                                        WebRequest request) {
        List<String> reasons = new ArrayList<>();
        for (ConstraintViolation error : ex.getConstraintViolations()) {
            reasons.add(String.format("%s - %s", error.getPropertyPath(), error.getMessage()));
        }

        return ResponseEntity.status(ApiError.VALIDATION_ERROR.getHttpStatus())
                .body(new ErrorDTO(ApiError.VALIDATION_ERROR.getMessage(), reasons));
    }

}
