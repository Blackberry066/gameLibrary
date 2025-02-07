package com.example.gameLibrary.controller;

import com.example.gameLibrary.application.GameServiceInterface;
import com.example.gameLibrary.application.LibraryServiceInterface;
import com.example.gameLibrary.application.LibraryUserServiceInterface;
import com.example.gameLibrary.controller.converter.GameDTOConverter;
import com.example.gameLibrary.controller.converter.LibraryDTOConverter;
import com.example.gameLibrary.controller.converter.LibraryUserDTOConverter;
import com.example.gameLibrary.controller.dto.GameDTO;
import com.example.gameLibrary.controller.dto.LibraryUserDTO;
import com.example.gameLibrary.model.LibraryUser;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Get all users")
    @GetMapping
    private Collection<LibraryUserDTO> getUsers() {
        return libraryUserService.getAllLibraryUsers().stream().map(user ->
                libraryUserDTOConverter.toDTO(user)).toList();
    }

    @Operation(summary = "Check if user already registered")
    @GetMapping(path = "checkUser/{username}")
    private LibraryUserDTO checkUser(@PathVariable String username) {
        LibraryUser someUser = libraryUserService.getLibraryUserByUsername(username);
        if (someUser != null) {
            return libraryUserDTOConverter.toDTO(someUser);
        }
        return null;
    }

    @Operation(summary = "Get some user")
    @GetMapping(path = "{userId}")
    private LibraryUserDTO getUser(@PathVariable Long userId) {
        return libraryUserDTOConverter.toDTO(libraryUserService.getLibraryUserById(userId));
    }

    @Operation(summary = "Gets all games intersections from first and second user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Both users found and intersections returned"),
            @ApiResponse(responseCode = "500", description = "Can't find first and/or second user")
    })
    @GetMapping(path = "shared_games")
    private Collection<GameDTO> getGamesIntersection(
            @RequestParam("u1") Long firstUserId, @RequestParam("u2") Long secondUserId
    )
    {
        LibraryUser firstUser = libraryUserService.getLibraryUserById(firstUserId);
        LibraryUser secondUser = libraryUserService.getLibraryUserById(secondUserId);
        return gameService.getGamesIntersection(firstUser, secondUser).stream().map(game ->
                gameDTOConverter.toDTO(game)).toList();
    }


    @Operation(summary = "User registration")
    @PostMapping(path = "register")
    private LibraryUserDTO createUser(@RequestBody LibraryUserDTO libraryUserDTO) {
        if (libraryUserService.getLibraryUserByUsername(libraryUserDTO.getUsername()) == null) {
            return libraryUserDTOConverter.toDTO(libraryUserService.registerUser(libraryUserDTOConverter
                    .toEntity(libraryUserDTO)));
        }
        return null;


    }

    @Operation(summary = "User id update")
    @PutMapping(path = "{id}")
    private LibraryUserDTO updateUser(@PathVariable("id") Long someNewId,
                                     @RequestBody LibraryUserDTO libraryUserDTO) {
        LibraryUser someUser = libraryUserDTOConverter.toEntity(libraryUserDTO);
        someUser.setId(someNewId);
        return libraryUserDTOConverter.toDTO(libraryUserService.updateLibraryUser(someUser));
    }

    @Operation(summary = "User deletion")
    @DeleteMapping(path = "{id}")
    private void deleteUser(@PathVariable("id") Long someId) {
        libraryUserService.deleteLibraryUser(someId);
    }

}
