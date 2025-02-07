package com.example.gameLibrary.controller.converter;

import com.example.gameLibrary.application.GameService;
import com.example.gameLibrary.application.GameServiceInterface;
import com.example.gameLibrary.application.LibraryUserServiceInterface;
import com.example.gameLibrary.controller.dto.LibraryDTO;
import com.example.gameLibrary.model.Library;
import jakarta.persistence.Column;
import org.springframework.stereotype.Component;


@Component
public class LibraryDTOConverter implements DTOConverter<LibraryDTO, Library> {
    GameServiceInterface gameService;
    LibraryUserServiceInterface libraryUserService;

    LibraryDTOConverter(GameServiceInterface gameService, LibraryUserServiceInterface libraryUserService) {
        this.gameService = gameService;
        this.libraryUserService = libraryUserService;
    }

    @Override
    public LibraryDTO toDTO(Library library) {
        if(library.getGames() != null) {
            if(library.getLibraryOwner()!= null) {
                return new LibraryDTO(library.getId(), library.getGameCount(),
                        library.getGames().stream().map(game-> game.getId()).toList(), library.getLibraryOwner().getId());
            }
            return new LibraryDTO(library.getId(), library.getGameCount(),
                    library.getGames().stream().map(game-> game.getId()).toList(), null);

        }
        return new LibraryDTO(library.getId(), library.getGameCount(),
                null, null);


    }

    @Override
    public Library toEntity(LibraryDTO libraryDTO) {
        if (libraryDTO.getGamesIds()!= null) {
            if (libraryUserService.getLibraryUserById(libraryDTO.getLibraryOwnerId())!=null)
            {
                return new Library(libraryDTO.getId(), libraryDTO.getGameCount(),
                        libraryDTO.getGamesIds().stream().map(gameId -> gameService.getGameById(gameId)).toList(),
                        libraryUserService.getLibraryUserById(libraryDTO.getLibraryOwnerId()));
            }
            return new Library(libraryDTO.getId(), libraryDTO.getGameCount(),
                    libraryDTO.getGamesIds().stream().map(gameId -> gameService.getGameById(gameId)).toList(), null);
        }
        return new Library(libraryDTO.getId(), libraryDTO.getGameCount(),null, null);

    }
}
