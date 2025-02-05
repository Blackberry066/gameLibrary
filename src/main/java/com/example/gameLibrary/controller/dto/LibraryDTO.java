package com.example.gameLibrary.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Collection;


@Getter
@AllArgsConstructor
public class LibraryDTO {
    private Long id;
    private Integer gameCount;
    private Collection<Long> gamesIds;
    private Long libraryOwnerId;
}
