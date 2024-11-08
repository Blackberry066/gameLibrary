package com.example.gameLibrary.repository;

import com.example.gameLibrary.model.LibraryUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibraryUserRepository extends CrudRepository<LibraryUser, Long> {
}
