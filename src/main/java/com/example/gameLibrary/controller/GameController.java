package com.example.gameLibrary.controller;

import com.example.gameLibrary.model.Game;
import com.example.gameLibrary.repository.GameRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/rest/api/games")
public class GameController {
    private GameRepository gameRepository;

    @GetMapping(path = "{id}")
    public Game getGame(@PathVariable("id") long id) {
        return new Game();
    }


}
