package com.example.gameLibrary.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Game")
@Getter
@Setter
public class Game {
    @Id
    @GeneratedValue
    @Column(name = "id_game")
    private Long id;

    @NotBlank
    @Column(name = "title")
    private String title;

    @NotBlank
    @Column(name = "genre")
    private String genre;

    @NotBlank
    @Column(name = "price")
    private Integer price;

    @Column(name = "release_date")
    private Integer releaseYear;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Library.class)
    @JoinColumn(name = "owned_library")
    private Library ownedLibrary;

    @ManyToMany(targetEntity = LibraryUser.class)
    @JoinTable(
            name = "Players",
            joinColumns = @JoinColumn(name = "id_library_user"),
            inverseJoinColumns = @JoinColumn(name = "id_game")
    )
    private Collection<LibraryUser> players;
}
