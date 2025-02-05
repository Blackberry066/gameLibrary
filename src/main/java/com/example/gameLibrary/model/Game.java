package com.example.gameLibrary.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Game")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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

    @ManyToMany(targetEntity = Library.class, mappedBy = "games")
    private Collection<Library> libraries;

//    @ManyToMany(targetEntity = LibraryUser.class)
//    @JoinTable(
//            name = "Players",
//            joinColumns = @JoinColumn(name = "id_library_user"),
//            inverseJoinColumns = @JoinColumn(name = "id_game")
//    )
//    private Collection<LibraryUser> players;
}
