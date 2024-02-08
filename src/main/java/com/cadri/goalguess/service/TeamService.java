package com.cadri.goalguess.service;

import com.cadri.goalguess.dto.TeamDTO;
import com.cadri.goalguess.repository.TeamRepository;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService {
    private TeamRepository teamRepository;
    private ConversionService conversionService;

    public TeamService(TeamRepository teamRepository, ConversionService conversionService) {
        this.teamRepository = teamRepository;
        this.conversionService = conversionService;
    }


}
