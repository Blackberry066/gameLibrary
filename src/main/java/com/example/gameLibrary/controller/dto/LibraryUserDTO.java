package com.example.gameLibrary.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Collection;
import java.util.Date;

@Getter
@AllArgsConstructor
public class LibraryUserDTO {
    private final Long id;
    private final String username;
    private final String password;
    private final String name;
    private final String surname;
    private final Date registrationDate;
    private final Long ownedLibraryId;

}
