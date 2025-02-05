package com.example.gameLibrary.repository;

import com.example.gameLibrary.model.Game;
import com.example.gameLibrary.model.Library;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.List;

@Repository
public interface LibraryRepository extends JpaRepository<Library, Long> {
    @Query(value = "SELECT g from Game g WHERE :firstLibrary MEMBER OF g.libraries AND :secondLibrary MEMBER OF g.libraries")
    Collection<Game> findGameIntersection(@Param("firstLibrary") Library firstLibrary,
                                                 @Param("secondLibrary") Library secondLibrary);

}
