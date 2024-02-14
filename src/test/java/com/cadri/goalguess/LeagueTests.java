package com.cadri.goalguess;

import com.cadri.goalguess.controller.LeagueController;
import com.cadri.goalguess.dto.TeamDTO;
import org.json.JSONObject;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.cadri.goalguess.dto.LeagueDTO;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LeagueTests {
    @Autowired
    private WebApplicationContext applicationContext;

    @Autowired
    private LeagueController leagueController;

    private MockMvc mockMvc;


    @BeforeEach
    void setup(){
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(applicationContext)
                .build();

    }

    @Test
    @Order(1)
    void createLeague() throws Exception {
        var result = this.mockMvc.perform(
                post("/leagues")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Kings League\"}")
        ).andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists()).andReturn();




    }

    @Test
    @Order(2)
    void createTeams() throws Exception {
        long leagueId = 1L;

        String teamJson = "{\"name\":\"Aniquiladores\"}";
        String team2Json = "{\"name\":\"Ultimate Mostoles\"}";

        mockMvc.perform(
                post("/leagues/" + leagueId + "/teams")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(teamJson)
        ).andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists());

        mockMvc.perform(
                post("/leagues/" + leagueId + "/teams")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(team2Json)
        ).andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists());
    }

    @Test
    @Order(3)
    void createMatchday() throws Exception {
        long leagueId = 1L;

        String matchdayJson = "{\"name\":\"Jornada 1\", \"date\":\"2025-10-10\", \"matches\": [" +
                "{\"homeTeam\": \"Aniquiladores\", \"awayTeam\": \"Ultimate Mostoles\"}" + "]}";

        mockMvc.perform(
                post("/leagues/" + leagueId + "/matchdays")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(matchdayJson)
        ).andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists());
    }

    @Test
    @Order(4)
    void createPrediction() throws Exception {

        String predictionJson = "{\"predicter\": \"cadri\", \"results\": [{\"matchId\": 1, \"homeGoals\": 2, \"awayGoals\": 1, \"penalties\": false, \"winner\": \"Aniquiladores\"}]}";

        mockMvc.perform(
                post("/matchdays/1/predictions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(predictionJson)
        ).andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists());
    }

    @Test
    @Order(5)
    void createMatchResults() throws Exception {
        String matchResultsJson = "{\"results\": [{\"matchId\": 1, \"homeGoals\": 2, \"awayGoals\": 1, \"penalties\": false, \"winner\": \"Aniquiladores\"}]}";

        mockMvc.perform(
                post("/matchdays/1/results")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(matchResultsJson)
        ).andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists());
    }

}
