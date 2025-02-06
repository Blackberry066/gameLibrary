package com.example.gameLibrary.repository;

import com.example.gameLibrary.model.LibraryUser;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibraryUserRepository extends JpaRepository<LibraryUser, Long> {
    LibraryUser findByUsername(String username);

    boolean existsByUsername(@NotBlank String username);
}
