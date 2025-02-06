package com.example.gameLibrary.application;

import com.example.gameLibrary.model.LibraryUser;
import jakarta.persistence.EntityNotFoundException;

import java.util.Collection;

public interface LibraryUserServiceInterface {
    LibraryUser getLibraryUserById(Long id) throws EntityNotFoundException;
    LibraryUser createLibraryUser(LibraryUser libraryUser) throws IllegalArgumentException;
    void deleteLibraryUser(Long id) throws EntityNotFoundException;
    LibraryUser updateLibraryUser(LibraryUser libraryUser) throws IllegalArgumentException;
    Collection<LibraryUser> getAllLibraryUsers();

    LibraryUser changeUserUsernameById(Long id, String newUsername) throws EntityNotFoundException;
    LibraryUser registerUser(LibraryUser libraryUser) throws IllegalArgumentException;
    LibraryUser getLibraryUserByUsername(String username) throws IllegalArgumentException;
}
