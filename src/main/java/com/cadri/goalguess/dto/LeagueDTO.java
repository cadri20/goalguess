package com.cadri.goalguess.dto;

import com.cadri.goalguess.model.Match;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
public class LeagueDTO {
    private Long id;
    private String name;
    private String logoUrl;
    private List<TeamDTO> teams;

}
