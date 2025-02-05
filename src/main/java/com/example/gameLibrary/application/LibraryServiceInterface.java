package com.example.gameLibrary.application;

import com.example.gameLibrary.model.Game;
import com.example.gameLibrary.model.Library;
import com.example.gameLibrary.model.LibraryUser;
import jakarta.persistence.EntityNotFoundException;

import java.util.Collection;

public interface LibraryServiceInterface {
    Library getLibraryById(Long id) throws EntityNotFoundException;
    Library createLibrary(Library library);
    void deleteLibraryById(Long id) throws EntityNotFoundException;

    Library updateLibrary(Library library) throws IllegalArgumentException;
    Collection<Library> getAllLibraries();

    void addGameToLibrary(Long libraryId, Long gameId) throws EntityNotFoundException;
    void removeGameFromLibrary(Long libraryId, Long gameId) throws EntityNotFoundException;



}
