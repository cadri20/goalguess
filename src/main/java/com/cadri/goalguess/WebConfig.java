package com.cadri.goalguess;

import com.cadri.goalguess.mapper.MatchdayRequestDTOMapper;
import com.cadri.goalguess.mapper.MatchdayResultRequestDTOMapper;
import com.cadri.goalguess.repository.MatchRepository;
import com.cadri.goalguess.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    MatchRepository matchRepository;

    public WebConfig(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
        this.matchRepository = matchRepository;
    }
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new MatchdayRequestDTOMapper(teamRepository));
        registry.addConverter(new MatchdayResultRequestDTOMapper(teamRepository, matchRepository));
    }
}
