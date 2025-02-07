package com.example.gameLibrary.controller;

import com.example.gameLibrary.application.*;
import com.example.gameLibrary.controller.converter.GameDTOConverter;
import com.example.gameLibrary.controller.converter.LibraryDTOConverter;
import com.example.gameLibrary.controller.converter.LibraryUserDTOConverter;
import com.example.gameLibrary.controller.dto.GameDTO;
import com.example.gameLibrary.controller.dto.LibraryDTO;
import com.example.gameLibrary.model.Game;
import com.example.gameLibrary.model.Library;
import com.example.gameLibrary.repository.LibraryRepository;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/rest/api/libraries")
public class LibraryController {
    private final GameServiceInterface gameService;
    private final LibraryServiceInterface libraryService;
    private final LibraryUserServiceInterface libraryUserService;

    private final GameDTOConverter gameDTOConverter;
    private final LibraryDTOConverter libraryDTOConverter;
    private final LibraryUserDTOConverter libraryUserDTOConverter;

    LibraryController(GameServiceInterface gameService, LibraryServiceInterface libraryService,
                      LibraryUserServiceInterface libraryUserService, GameDTOConverter gameDTOConverter,
                      LibraryDTOConverter libraryDTOConverter, LibraryUserDTOConverter libraryUserDTOConverter) {
        this.gameService = gameService;
        this.libraryService = libraryService;
        this.libraryUserService = libraryUserService;
        this.gameDTOConverter = gameDTOConverter;
        this.libraryDTOConverter = libraryDTOConverter;
        this.libraryUserDTOConverter = libraryUserDTOConverter;
    }

    @Operation(summary = "Returns all libraries")
    @GetMapping
    private Collection<LibraryDTO> getLibraries() {
        return libraryService.getAllLibraries().stream()
                .map(library -> libraryDTOConverter.toDTO(library)).toList();
    }

    @Operation(summary = "Return all games from library with id")
    @GetMapping(path = "{libraryId}")
    private Collection<GameDTO> getGames(@PathVariable("libraryId") Long libraryId) {
        return libraryService.getLibraryById(libraryId).getGames().stream()
                .map(game -> gameDTOConverter.toDTO(game)).toList();
    }

    @Operation(summary = "Creates new library")
    @PostMapping
    private LibraryDTO createLibrary(@RequestBody LibraryDTO libraryDTO) {
        return libraryDTOConverter.toDTO(libraryService.createLibrary(libraryDTOConverter.toEntity(libraryDTO)));
    }

    @Operation(summary = "Add game to library with id")
    @PostMapping(path = "{id}")
    private void addGameToLibrary(@PathVariable("id") Long libraryId, @RequestBody GameDTO gameDTO) {
        Game someGame = gameService.getGameByTitle(gameDTO.getTitle());
        Library someLibrary = libraryService.getLibraryById(libraryId);
        if (someLibrary.getGames().stream().map(game -> game.getId()).toList().contains(someGame.getId()))
            return;
        someLibrary.setGameCount(someLibrary.getGameCount() + 1);
        libraryService.updateLibrary(someLibrary);
        libraryService.addGameToLibrary(libraryId, someGame.getId());
    }

    @Operation(summary = "Delete game from library")
    @DeleteMapping(path = "{id}")
    private void removeGameFromLibrary(@PathVariable("id") Long libraryId, @RequestBody GameDTO gameDTO) {
        Game someGame = gameService.getGameById(gameDTO.getId());
        Library someLibrary = libraryService.getLibraryById(libraryId);
        someLibrary.setGameCount(someLibrary.getGameCount() - 1);
        libraryService.updateLibrary(someLibrary);
        libraryService.removeGameFromLibrary(libraryId, someGame.getId());
    }

    @Operation(summary = "Change library id")
    @PutMapping(path = "{id}")
    private LibraryDTO updateLibrary(@PathVariable("id") Long libraryId, @RequestBody LibraryDTO libraryDTO) {
        Library someLibrary = libraryService.getLibraryById(libraryDTO.getId());
        someLibrary.setId(libraryId);
        return libraryDTOConverter.toDTO(libraryService.updateLibrary(someLibrary));
    }

    @Operation(summary = "Removes library")
    @DeleteMapping(path = "library/{id}")
    private void deleteLibrary(@PathVariable("id") Long libraryId) {
        libraryService.deleteLibraryById(libraryId);
    }
}
