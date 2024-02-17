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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

        String[] teams = {"Aniquiladores", "Ultimate Mostoles", "Porcinos", "El Barrio"};

        for (String team : teams) {
            mockMvc.perform(
                    post("/leagues/" + leagueId + "/teams")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{\"name\":\"" + team + "\"}")
            ).andExpect(status().isOk())
                    .andExpect(jsonPath("$.id").exists());
        }
    }

    @Test
    @Order(3)
    void createMatchday() throws Exception {
        long leagueId = 1L;

        String matchdayJson = "{\"name\":\"Jornada 1\", \"date\":\"2025-10-10\", \"matches\": [" +
                "{\"homeTeam\": \"Aniquiladores\", \"awayTeam\": \"Ultimate Mostoles\"}," +
                "{\"homeTeam\": \"Porcinos\", \"awayTeam\": \"El Barrio\"}]}";

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

        String predictionJson = "{\"predicter\": \"cadri\", \"results\": [" +
                "{\"matchId\": 1, \"homeGoals\": 2, \"awayGoals\": 1, \"penalties\": false, \"winner\": \"Aniquiladores\"}," +
                "{\"matchId\": 2, \"homeGoals\": 1, \"awayGoals\": 1, \"penalties\": true, \"winner\": \"Porcinos\"}]}";

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
        String matchResultsJson = "{\"results\": [" +
                "{\"matchId\": 1, \"homeGoals\": 5, \"awayGoals\": 1, \"penalties\": false, \"winner\": \"Aniquiladores\"}," +
                "{\"matchId\": 2, \"homeGoals\": 1, \"awayGoals\": 2, \"penalties\": false, \"winner\": \"El Barrio\"}]}";

        mockMvc.perform(
                post("/matchdays/1/results")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(matchResultsJson)
        ).andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists());
    }

    @Test
    @Order(5)
    void getPredictionPoints() throws Exception {
        mockMvc.perform(get("/predictions/1/points"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.points").value("62.5"));
    }

}
