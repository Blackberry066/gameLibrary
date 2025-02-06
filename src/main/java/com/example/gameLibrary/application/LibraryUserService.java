package com.example.gameLibrary.application;

import com.example.gameLibrary.model.Game;
import com.example.gameLibrary.model.Library;
import com.example.gameLibrary.model.LibraryUser;
import com.example.gameLibrary.repository.LibraryRepository;
import com.example.gameLibrary.repository.LibraryUserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class LibraryUserService implements LibraryUserServiceInterface {

    private final LibraryRepository libraryRepository;
    LibraryUserRepository libraryUserRepository;

    LibraryUserService(LibraryUserRepository libraryUserRepository, LibraryRepository libraryRepository) {
        this.libraryUserRepository = libraryUserRepository;
        this.libraryRepository = libraryRepository;
    }

    @Override
    public LibraryUser getLibraryUserById(Long id) throws EntityNotFoundException {
        return libraryUserRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Could not find library user with id " + id));
    }




    @Override
    public LibraryUser createLibraryUser(LibraryUser libraryUser) throws IllegalArgumentException {
        Date today = new Date();
        if (libraryUser.getRegistrationDate().after(today)) {
            throw new IllegalArgumentException("Registration date cannot be later than current date");
        }
        return libraryUserRepository.save(libraryUser);

    }

    @Override
    public void deleteLibraryUser(Long id) throws EntityNotFoundException {
        if (libraryUserRepository.existsById(id)) {
            libraryUserRepository.deleteById(id);
        }
        throw new EntityNotFoundException("Could not find library user with id " + id);

    }

    @Override
    public LibraryUser updateLibraryUser(LibraryUser libraryUser) throws IllegalArgumentException {
        if (libraryUserRepository.existsById(libraryUser.getId())) {
            return libraryUserRepository.save(libraryUser);
        }
        throw new IllegalArgumentException("Could not find library user with id " + libraryUser.getId());
    }

    @Override
    public Collection<LibraryUser> getAllLibraryUsers() {
        return libraryUserRepository.findAll();
    }

    @Override
    public LibraryUser changeUserUsernameById(Long id, String newUsername) throws EntityNotFoundException {
        if(libraryUserRepository.existsById(id)) {
            LibraryUser someUser = libraryUserRepository.getReferenceById(id);
            someUser.setUsername(newUsername);
            return someUser;
        }
        throw new EntityNotFoundException("Could not find library user with id " + id);
    }

    @Override
    public LibraryUser registerUser(LibraryUser libraryUser) throws IllegalArgumentException {
        libraryUser.setRegistrationDate(new Date());
        Library someNewLibrary = new Library(null, 0, List.of(), libraryUser);

        Library savedLibrary = libraryRepository.save(someNewLibrary);
        libraryUser.setUserLibrary(savedLibrary);

        return libraryUserRepository.save(libraryUser);

    }


}
