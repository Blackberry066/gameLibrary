package com.example.gameLibrary;

import com.example.gameLibrary.application.LibraryService;
import com.example.gameLibrary.model.Library;
import com.example.gameLibrary.model.LibraryUser;
import com.example.gameLibrary.repository.GameRepository;
import com.example.gameLibrary.repository.LibraryRepository;
import com.example.gameLibrary.repository.LibraryUserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LibraryServiceUnitTest {

    @Test
    public void testLibraryFindAll() {
        LibraryRepository libraryRepository = mock(LibraryRepository.class);
        LibraryUserRepository libraryUserRepository = mock(LibraryUserRepository.class);
        GameRepository gameRepository = mock(GameRepository.class);

        when(libraryRepository.findAll()).thenReturn(
                List.of(
                        new Library(0l, 0, List.of(), new LibraryUser()),
                        new Library(1l, 1, List.of(), new LibraryUser()),
                        new Library(2l, 2, List.of(), new LibraryUser())
                )
        );

        LibraryService libraryService = new LibraryService(libraryRepository, gameRepository, libraryUserRepository);
        List<Library> libraries = (List<Library>) libraryService.getAllLibraries();
        Assertions.assertEquals(0, libraries.get(0).getGameCount());
        Assertions.assertEquals(1, libraries.get(1).getGameCount());
        Assertions.assertEquals(2, libraries.get(2).getGameCount());
    }

    @Test
    public void testLibraryGetById() {
        LibraryRepository libraryRepository = mock(LibraryRepository.class);
        LibraryUserRepository libraryUserRepository = mock(LibraryUserRepository.class);
        GameRepository gameRepository = mock(GameRepository.class);

        LibraryService libraryService = new LibraryService(libraryRepository, gameRepository, libraryUserRepository);

        when(libraryRepository.findById(1L)).thenReturn(
                Optional.of(new Library(1L, 1, List.of(), new LibraryUser()))
        );

        Assertions.assertEquals(1, libraryService.getLibraryById(1L).getGameCount());
    }
}
