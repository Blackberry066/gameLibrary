package com.example.gameLibrary.application;

import com.example.gameLibrary.model.Game;
import com.example.gameLibrary.model.Library;
import com.example.gameLibrary.model.LibraryUser;
import com.example.gameLibrary.repository.GameRepository;
import com.example.gameLibrary.repository.LibraryRepository;
import com.example.gameLibrary.repository.LibraryUserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class LibraryService implements LibraryServiceInterface{

    LibraryRepository libraryRepository;
    GameRepository gameRepository;
    LibraryUserRepository libraryUserRepository;
    public LibraryService(LibraryRepository libraryRepository,
                          GameRepository gameRepository, LibraryUserRepository libraryUserRepository) {
        this.libraryRepository = libraryRepository;
        this.gameRepository = gameRepository;
        this.libraryUserRepository = libraryUserRepository;
    }

    @Override
    public Library getLibraryById(Long id) throws EntityNotFoundException {
        return libraryRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Library not found with id " + id));
    }

    @Override
    public Library createLibrary(Library library) {
        return libraryRepository.save(library);
    }

    @Override
    public void deleteLibraryById(Long id) throws EntityNotFoundException {
        if (libraryRepository.existsById(id)) {
            libraryRepository.deleteById(id);
        }
        throw new EntityNotFoundException("Library not found with id " + id);
    }


    @Override
    public Library updateLibrary(Library library) throws IllegalArgumentException{
        if (libraryRepository.existsById(library.getId())) {
            return libraryRepository.save(library);
        }
        throw new IllegalArgumentException("Library not exist");
    }

    @Override
    public Collection<Library> getAllLibraries() {
        return libraryRepository.findAll();
    }

    @Override
    public void addGameToLibrary(Long libraryId, Long gameId) throws EntityNotFoundException {
        if (libraryRepository.existsById(libraryId)) {
            if (gameRepository.existsById(gameId)) {
                Library someLibrary = libraryRepository.getReferenceById(libraryId);
                Game someGame = gameRepository.getReferenceById(gameId);
                someLibrary.getGames().add(someGame);

                libraryRepository.save(someLibrary);
                gameRepository.save(someGame);

                return;
            }
            throw new EntityNotFoundException("Game not found with id " + gameId);
        }
        throw new EntityNotFoundException("Library not found with id " + libraryId);
    }

    @Override
    public void removeGameFromLibrary(Long libraryId, Long gameId)
            throws EntityNotFoundException, IllegalArgumentException {
        if (libraryRepository.existsById(libraryId)) {
            if (gameRepository.existsById(gameId)) {
                Library someLibrary = libraryRepository.getReferenceById(libraryId);


                Game someGame = gameRepository.getReferenceById(gameId);
                String gameTitles = someLibrary.getGames()
                        .stream()
                        .map(game -> {
                            return game.getTitle();
                        })
                        .collect(Collectors.joining(", "));
                String gameIds = someLibrary.getGames()
                        .stream()
                        .map(game -> String.valueOf(game.getId())) // Преобразуем id в строку
                        .collect(Collectors.joining(", "));
                if (someLibrary.getGames().contains(someGame)) {
                    someGame.getLibraries().remove(someLibrary);
                    gameRepository.save(someGame);

                    someLibrary.getGames().remove(someGame);
                    libraryRepository.save(someLibrary);
                    return;


                }


                throw new IllegalArgumentException("Game " + gameId + " not in library. Existing games: " + gameTitles +
                        " with IDs " + gameIds);
            }
            throw new EntityNotFoundException("Game not found with id " + gameId);
        }
        throw new EntityNotFoundException("Library not found with id " + libraryId);
    }

    @Override
    public Library setLibraryOwnerById(Long libraryId, Long ownerId) throws EntityNotFoundException {
        if (libraryRepository.existsById(libraryId)) {
            if (libraryUserRepository.existsById(ownerId)) {
                Library someLibrary = libraryRepository.getReferenceById(libraryId);
                LibraryUser someNewOwner = libraryUserRepository.getReferenceById(ownerId);
                someLibrary.setLibraryOwner(someNewOwner);
                return someLibrary;
            }
            throw new EntityNotFoundException("User not found with id " + ownerId);
        }
        throw new EntityNotFoundException("Library not found with id " + libraryId);
    }

    @Override
    public Library removeLibraryOwnerById(Long libraryId) throws EntityNotFoundException {
        if (libraryRepository.existsById(libraryId)) {
            Library someLibrary = libraryRepository.getReferenceById(libraryId);
            someLibrary.setLibraryOwner(null);
            return someLibrary;
        }
        throw new EntityNotFoundException("Library not found with id " + libraryId);
    }




}
