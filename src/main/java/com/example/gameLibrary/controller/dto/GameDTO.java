package com.example.gameLibrary.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Collection;

@Getter
@AllArgsConstructor
public class GameDTO {
    private final Long id;
    private final String title;
    private final String genre;
    private final Integer price;
    private final Integer year;
    private final Collection<Long> ownedLibrariesIds;


}
