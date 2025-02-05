package com.example.gameLibrary.controller;

import com.example.gameLibrary.application.GameServiceInterface;
import com.example.gameLibrary.application.LibraryServiceInterface;
import com.example.gameLibrary.application.LibraryUserServiceInterface;
import com.example.gameLibrary.controller.converter.GameDTOConverter;
import com.example.gameLibrary.controller.converter.LibraryDTOConverter;
import com.example.gameLibrary.controller.converter.LibraryUserDTOConverter;
import com.example.gameLibrary.controller.dto.LibraryUserDTO;
import com.example.gameLibrary.model.LibraryUser;
import com.example.gameLibrary.repository.LibraryUserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/rest/api/users")
public class LibraryUserController {
    private final GameServiceInterface gameService;
    private final LibraryServiceInterface libraryService;
    private final LibraryUserServiceInterface libraryUserService;

    private final GameDTOConverter gameDTOConverter;
    private final LibraryDTOConverter libraryDTOConverter;
    private final LibraryUserDTOConverter libraryUserDTOConverter;

    LibraryUserController(GameServiceInterface gameService, LibraryServiceInterface libraryService,
                          LibraryUserServiceInterface libraryUserService, GameDTOConverter gameDTOConverter,
                          LibraryDTOConverter libraryDTOConverter, LibraryUserDTOConverter libraryUserDTOConverter) {
        this.gameService = gameService;
        this.libraryService = libraryService;
        this.libraryUserService = libraryUserService;
        this.gameDTOConverter = gameDTOConverter;
        this.libraryDTOConverter = libraryDTOConverter;
        this.libraryUserDTOConverter = libraryUserDTOConverter;
    }
    @GetMapping
    private Collection<LibraryUserDTO> getUsers() {
        return libraryUserService.getAllLibraryUsers().stream().map(user ->
                libraryUserDTOConverter.toDTO(user)).toList();
    }

    @GetMapping(path = "{userId}")
    private LibraryUserDTO getUser(@PathVariable Long userId) {
        return libraryUserDTOConverter.toDTO(libraryUserService.getLibraryUserById(userId));
    }

    @PostMapping
    private LibraryUserDTO createUser(@RequestBody LibraryUserDTO libraryUserDTO) {
        return libraryUserDTOConverter.toDTO(libraryUserService.createLibraryUser(libraryUserDTOConverter
                .toEntity(libraryUserDTO)));

    }

    @PutMapping(path = "{id}")
    private LibraryUserDTO updateUser(@PathVariable("id") Long someNewId,
                                     @RequestBody LibraryUserDTO libraryUserDTO) {
        LibraryUser someUser = libraryUserDTOConverter.toEntity(libraryUserDTO);
        someUser.setId(someNewId);
        return libraryUserDTOConverter.toDTO(libraryUserService.updateLibraryUser(someUser));
    }

    @DeleteMapping(path = "{id}")
    private void deleteUser(@PathVariable("id") Long someId) {
        libraryUserService.deleteLibraryUser(someId);
    }

}
