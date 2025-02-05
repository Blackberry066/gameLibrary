package com.example.gameLibrary.application;

import com.example.gameLibrary.model.Game;
import com.example.gameLibrary.model.Library;
import com.example.gameLibrary.model.LibraryUser;
import com.example.gameLibrary.repository.GameRepository;
import com.example.gameLibrary.repository.LibraryRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
@Transactional
public class LibraryService implements LibraryServiceInterface{

    LibraryRepository libraryRepository;
    GameRepository gameRepository;
    LibraryService(LibraryRepository libraryRepository, GameRepository gameRepository) {
        this.libraryRepository = libraryRepository;
        this.gameRepository = gameRepository;
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
            libraryRepository.save(library);
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
                libraryRepository.getReferenceById(libraryId).getGames()
                        .add(gameRepository.getReferenceById(gameId));
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
                if (someLibrary.getGames().contains(someGame)) {
                    someLibrary.getGames().remove(someGame);
                }
                throw new IllegalArgumentException("Game " + gameId + " not in library " + libraryId);
            }
            throw new EntityNotFoundException("Game not found with id " + gameId);
        }
        throw new EntityNotFoundException("Library not found with id " + libraryId);
    }


}
