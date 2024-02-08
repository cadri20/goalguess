package com.cadri.goalguess.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
@Setter
public class RestException extends RuntimeException{
    private HttpStatus status;
    private String description;
    private List<String> reasons;

    public RestException(HttpStatus status, String description, List<String> reasons) {
        this.status = status;
        this.description = description;
        this.reasons = reasons;
    }

    public RestException(HttpStatus status, String description) {
        this.status = status;
        this.description = description;
    }
}
