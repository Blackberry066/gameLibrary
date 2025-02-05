package com.example.gameLibrary.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Collection;


@Getter
@AllArgsConstructor
public class LibraryDTO {
    private final Long id;
    private final Integer gameCount;
    private final Collection<Long> gamesIds;
    private final Long libraryOwnerId;
}
