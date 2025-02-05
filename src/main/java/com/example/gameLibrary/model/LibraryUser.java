package com.example.gameLibrary.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "Library_User")
@Getter
@Setter
public class LibraryUser {
    @Id
    @GeneratedValue
    @Column(name = "id_library_user")
    private Long id;

    @NotBlank
    @Column(name = "username")
    private String username;

    @NotBlank
    @Column(name = "password")
    private String password;

    @NotBlank
    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "registration_date")
    private Date registrationDate;

    @OneToOne(targetEntity = Library.class, mappedBy = "libraryOwner")
    private Library ownedByUser;

    @ManyToMany(targetEntity = Game.class, mappedBy = "players")
    private Collection<Game> gamesPlaying;

}
