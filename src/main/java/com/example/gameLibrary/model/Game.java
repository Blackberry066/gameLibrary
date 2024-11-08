package com.example.gameLibrary.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Game {
    @Id
    @GeneratedValue
    private Long id;

    private String title;
    private String genre;

    private Integer price;
    private Integer releaseDate;

    @ManyToOne
    private Library library;
}
