package com.cadri.goalguess.controller;

import com.cadri.goalguess.repository.MatchdayRepository;
import org.springframework.core.convert.ConversionService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/matches")
public class MatchController {
    private MatchdayRepository matchdayRepository;
    private ConversionService conversionService;

    public MatchController(MatchdayRepository matchdayRepository, ConversionService conversionService) {
        this.matchdayRepository = matchdayRepository;
        this.conversionService = conversionService;
    }



}
