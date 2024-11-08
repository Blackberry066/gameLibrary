package com.example.gameLibrary.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

import java.util.Date;

@Entity
public class LibraryUser {
    @Id
    @GeneratedValue
    private Long id;

    private String username;
    private String password;

    private String name;
    private String surname;
    private Date registrationDate;

    @OneToOne
    private Library ownedByUser;

}
