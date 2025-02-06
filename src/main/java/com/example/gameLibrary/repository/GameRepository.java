package com.example.gameLibrary.repository;

import com.example.gameLibrary.model.Game;
import com.example.gameLibrary.model.LibraryUser;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
    boolean findByTitle(@NotBlank String title);

    boolean existsGameByTitle(@NotBlank String title);

    Game getReferenceByTitle(@NotBlank String title);
//    @Query(value = "SELECT g from Game g WHERE :playerA MEMBER OF g.players AND :playerB MEMBER OF g.players")
//    Collection<Game> findGameIntersection(@Param("playerA") LibraryUser a, @Param("playerB") LibraryUser b);
}
