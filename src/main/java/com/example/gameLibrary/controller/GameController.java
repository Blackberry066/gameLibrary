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
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("rest/api/games")
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


    @Operation(summary = "Returns all games from db")
    @GetMapping
    private Collection<GameDTO> getGames() {
        return gameService.getAllGames().stream().map(game -> gameDTOConverter.toDTO(game)).toList();
    }

    @Operation(summary = "Returns game with id")
    @GetMapping(path = "{id}")
    private GameDTO getGame(@PathVariable("id") long id) {
        return gameDTOConverter.toDTO(gameService.getGameById(id));
    }

    @Operation(summary = "Creates new game for library")
    @PostMapping
    public GameDTO createGame(@RequestBody GameDTO gameDTO) {
        return gameDTOConverter.toDTO(gameService.createGame(gameDTOConverter.toEntity(gameDTO)));
    }

    @Operation(summary = "Update game for new ID")
    @PutMapping(path = "{gameId}")
    private GameDTO updateGame(@RequestBody GameDTO gameDTO, @PathVariable("gameId") long gameId) {
        Game someGame = gameDTOConverter.toEntity(gameDTO);
        someGame.setId(gameId);
        return gameDTOConverter.toDTO(gameService.updateGame(someGame));
    }


    @Operation(summary = "Removes game from library")
    @DeleteMapping(path = "{gameId}")
    private void deleteGame(@PathVariable("gameId") long gameId) {
        gameService.deleteGameById(gameId);
    }





}
