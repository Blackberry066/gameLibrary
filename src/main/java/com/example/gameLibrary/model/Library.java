package com.example.gameLibrary.model;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.Date;

@Entity
public class Library {

    @Id
    @GeneratedValue
    private Long id;

    private int gameCount;

    @OneToMany
    private Collection<Game> games;

    @OneToOne
    private LibraryUser libraryOwner;


}
