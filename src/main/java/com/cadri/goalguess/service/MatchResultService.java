package com.cadri.goalguess.service;

import com.cadri.goalguess.dto.MatchDTO;
import com.cadri.goalguess.dto.MatchResultDTO;
import com.cadri.goalguess.model.MatchResult;
import com.cadri.goalguess.repository.MatchResultRepository;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

@Service
public class MatchResultService {
    private MatchResultRepository matchResultRepository;
    private ConversionService conversionService;

    public MatchResultService(MatchResultRepository matchResultRepository, ConversionService conversionService) {
        this.matchResultRepository = matchResultRepository;
        this.conversionService = conversionService;
    }
    public MatchResultDTO save(MatchResultDTO matchResult){
        var transformed = conversionService.convert(matchResult, MatchResult.class);
        return conversionService.convert(matchResultRepository.save(transformed), MatchResultDTO.class);
    }
}
