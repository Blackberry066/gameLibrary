package com.example.gameLibrary.controller.converter;

import com.example.gameLibrary.application.LibraryService;
import com.example.gameLibrary.application.LibraryServiceInterface;
import com.example.gameLibrary.application.LibraryUserServiceInterface;
import com.example.gameLibrary.controller.dto.GameDTO;
import com.example.gameLibrary.model.Game;
import com.example.gameLibrary.model.LibraryUser;
import com.example.gameLibrary.repository.LibraryRepository;
import org.springframework.stereotype.Component;


@Component
public class GameDTOConverter implements DTOConverter<GameDTO, Game> {
    private final LibraryServiceInterface libraryService;
    private final LibraryUserServiceInterface libraryUserService;

    GameDTOConverter(LibraryServiceInterface libraryService, LibraryUserServiceInterface libraryUserService) {
        this.libraryService = libraryService;
        this.libraryUserService = libraryUserService;
    }

    public GameDTO toDTO(Game someGame) {
        return new GameDTO(someGame.getId(), someGame.getTitle(), someGame.getGenre(), someGame.getPrice(),
                someGame.getReleaseYear(), someGame.getLibraries().stream()
                .map(library -> library.getId()).toList());
    }

    public Game toEntity(GameDTO someGameDTO) {
        return new Game(someGameDTO.getId(), someGameDTO.getTitle(), someGameDTO.getGenre(), someGameDTO.getPrice(),
                someGameDTO.getYear(), someGameDTO.getOwnedLibrariesIds().stream()
                .map(libraryId -> libraryService.getLibraryById(libraryId)).toList());
    }
}
