package com.example.gameLibrary.application;

import com.example.gameLibrary.model.Game;
import com.example.gameLibrary.model.LibraryUser;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;

import java.util.Collection;

public interface  GameServiceInterface {
    Game getGameById(Long id) throws EntityNotFoundException;
    Game createGame(Game game) throws IllegalArgumentException;
    void deleteGameById(Long id) throws EntityNotFoundException;
    Game updateGame(Game game) throws IllegalArgumentException;
    Collection<Game> getAllGames();

    Collection<Game> getGamesIntersection(LibraryUser first, LibraryUser second)
            throws EntityNotFoundException;

}
