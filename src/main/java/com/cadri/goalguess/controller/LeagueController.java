package com.cadri.goalguess.controller;

import com.cadri.goalguess.dto.LeagueDTO;
import com.cadri.goalguess.dto.TeamDTO;
import com.cadri.goalguess.service.LeagueService;
import com.cadri.goalguess.service.TeamService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/leagues")
public class LeagueController {
    static final Logger LOGGER = LoggerFactory.getLogger(LeagueController.class);
    private LeagueService leagueService;
    private TeamService teamService;

    @Autowired
    public LeagueController(LeagueService leagueService){
        this.leagueService = leagueService;
    }
    @GetMapping()
    public List<LeagueDTO> getLeagues(){
        return leagueService.findAll();
    }

    @PostMapping
    public LeagueDTO createLeague(@RequestBody LeagueDTO league){
        LOGGER.debug("League to save: {}", league);
        return leagueService.save(league);
    }

    @GetMapping("/{leagueId}/teams")
    public List<TeamDTO> getTeams(@PathVariable Long leagueId){
        return leagueService.getTeams(leagueId);
    }

    @PostMapping("/{leagueId}/teams")
    public TeamDTO createTeam(@PathVariable Long leagueId, @RequestBody TeamDTO team){
        return leagueService.createTeam(leagueId, team);
    }
}
