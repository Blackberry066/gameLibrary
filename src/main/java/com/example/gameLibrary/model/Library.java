package com.example.gameLibrary.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "Library")
@Getter
@Setter
public class Library {

    @Id
    @GeneratedValue
    @Column(name = "id_library")
    private Long id;

    @NotBlank
    @Column(name = "game_count")
    private int gameCount;

    @OneToMany(targetEntity = Game.class, mappedBy = "ownedLibrary", orphanRemoval = true)
    private Collection<Game> games;




    @OneToOne(targetEntity = LibraryUser.class)
    @JoinColumn(name = "library_owner")
    private LibraryUser libraryOwner;


}
