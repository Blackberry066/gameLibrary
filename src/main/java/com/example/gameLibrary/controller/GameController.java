package com.example.gameLibrary.controller;

import com.example.gameLibrary.application.GameServiceInterface;
import com.example.gameLibrary.application.LibraryServiceInterface;
import com.example.gameLibrary.application.LibraryUserServiceInterface;
import com.example.gameLibrary.controller.converter.GameDTOConverter;
import com.example.gameLibrary.controller.converter.LibraryDTOConverter;
import com.example.gameLibrary.controller.converter.LibraryUserDTOConverter;
import com.example.gameLibrary.controller.dto.GameDTO;
import com.example.gameLibrary.model.Game;
import com.example.gameLibrary.repository.GameRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/rest/api/games")
public class GameController {
    GameServiceInterface gameService;
    LibraryServiceInterface libraryService;
    LibraryUserServiceInterface libraryUserService;
    GameDTOConverter gameDTOConverter;
    LibraryDTOConverter libraryDTOConverter;
    LibraryUserDTOConverter libraryUserDTOConverter;

    public GameController(GameServiceInterface gameService, LibraryServiceInterface libraryService,
                          LibraryUserServiceInterface libraryUserService, GameDTOConverter gameDTOConverter,
                          LibraryDTOConverter libraryDTOConverter, LibraryUserDTOConverter libraryUserDTOConverter) {
        this.gameService = gameService;
        this.libraryService = libraryService;
        this.libraryUserService = libraryUserService;
        this.gameDTOConverter = gameDTOConverter;
        this.libraryDTOConverter = libraryDTOConverter;
        this.libraryUserDTOConverter = libraryUserDTOConverter;
    }

    private GameRepository gameRepository;

    @GetMapping
    private Collection<Game> getGames() {
        return gameRepository.findAll();
    }

    @GetMapping(path = "{id}")
    private GameDTO getGame(@PathVariable("id") long id) {
        return gameDTOConverter.toDTO(gameService.getGameById(id));
    }

    @PostMapping
    private GameDTO createGame(@RequestBody GameDTO gameDTO) {
        return gameDTOConverter.toDTO(gameService.createGame(gameDTOConverter.toEntity(gameDTO)));
    }

    @PutMapping(path = "{gameId}")
    private GameDTO updateGame(@RequestBody GameDTO gameDTO, @PathVariable("gameId") long gameId) {
        Game someGame = gameDTOConverter.toEntity(gameDTO);
        someGame.setId(gameId);
        return gameDTOConverter.toDTO(gameService.updateGame(someGame));
    }

    @DeleteMapping(path = "{gameId}")
    private void deleteGame(@PathVariable("gameId") long gameId) {
        gameRepository.deleteById(gameId);
    }



}
