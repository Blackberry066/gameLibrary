package com.example.gameLibrary;

import com.example.gameLibrary.application.GameService;
import com.example.gameLibrary.controller.GameController;
import com.example.gameLibrary.controller.dto.GameDTO;
import com.example.gameLibrary.model.Game;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.mockito.Mock;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collection;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class GameControllerIntegrationTest {
    @Autowired
    MockMvc mockMvc;

    @Test
    public void testGamesGet() throws Exception {
        mockMvc.perform(get("/rest/api/games")).andExpect(status().isOk());
    }

    @MockitoBean
    private GameService gameService;
    private ObjectMapper objectMapper = new ObjectMapper();
//    @MockitoBean
//    private GameController gameController;

    @Test
    public void testGameWithValues() throws Exception {
        when(gameService.getGameById(123L)).thenReturn(
                new Game(1L, "Witcher", "RPG", 200, 2000, List.of()));

        mockMvc.perform(get("/rest/api/games/"+123L)).andExpect(jsonPath("$.title")
                .value("Witcher"));
    }

    @Test
    public void testGameCreation() throws Exception {
        GameDTO someGameDTO = new GameDTO(1L, "Fallout", "Action", 45, 2015,List.of());
        Game someGame = new Game(1L, "Fallout", "Action", 45, 2015, List.of());

        when(gameService.createGame(any(Game.class))).thenReturn(someGame);

        mockMvc.perform(post("/rest/api/games").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(someGameDTO))).andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Fallout"));


    }



}
