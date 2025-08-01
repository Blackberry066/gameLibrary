package com.example.gameLibrary.application;

import com.example.gameLibrary.model.Game;
import com.example.gameLibrary.model.Library;
import com.example.gameLibrary.model.LibraryUser;
import com.example.gameLibrary.repository.GameRepository;
import com.example.gameLibrary.repository.LibraryRepository;
import com.example.gameLibrary.repository.LibraryUserRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Service
@Transactional
public class GameService implements GameServiceInterface{

    private final LibraryRepository libraryRepository;
    private final GameRepository gameRepository;
    private final LibraryUserRepository libraryUserRepository;


    public GameService(GameRepository gameRepository, LibraryUserRepository libraryUserRepository, LibraryRepository libraryRepository) {
        this.gameRepository = gameRepository;
        this.libraryUserRepository = libraryUserRepository;
        this.libraryRepository = libraryRepository;
    }

    @Override
    public Game getGameById(Long id) throws EntityNotFoundException {
        return gameRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Game with id: " + id + " not found"));
    }

    @Override
    public Collection<Game> getAllGames() {
        return gameRepository.findAll();
    }

    @Override
    public Collection<Game> getGamesIntersection(LibraryUser first, LibraryUser second)
            throws EntityNotFoundException {

        if (libraryUserRepository.existsById(first.getId())
                && libraryUserRepository.existsById(second.getId())) {
            return libraryRepository.findGameIntersection(first.getUserLibrary(), second.getUserLibrary());
        }
        throw new EntityNotFoundException("Some user not found");
    }

    @Override
    public Game getGameByTitle(String title) throws EntityNotFoundException {
        if(gameRepository.existsGameByTitle(title)) {
            return gameRepository.getReferenceByTitle(title);
        }
        throw new EntityNotFoundException("Game not found");
    }

    @Override
    public Game createGame(Game game) throws IllegalArgumentException {
        if (game.getReleaseYear() <= LocalDate.now().getYear()) {
            return gameRepository.save(game);
        }
        throw new IllegalArgumentException
                ("Game cannot be created with release year " + game.getReleaseYear());
    }

    @Override
    public void deleteGameById(Long id) throws EntityNotFoundException {
        if (gameRepository.existsById(id)) {
            gameRepository.deleteById(id);
        }
        throw new EntityNotFoundException("Game with id: " + id + " not found");
    }

    @Override
    public Game updateGame(Game game) throws IllegalArgumentException {
        if (gameRepository.existsById(game.getId())) {
            return gameRepository.save(game);
        }
        throw new IllegalArgumentException("Game with id: " + game.getId() + " not found");

    }

}
