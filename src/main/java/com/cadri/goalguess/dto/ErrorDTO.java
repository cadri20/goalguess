package com.cadri.goalguess.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class ErrorDTO {
    private String description;
    private List<String> reasons;

    public ErrorDTO(String description, List<String> reasons) {
        this.description = description;
        this.reasons = reasons;
    }


}
