package com.example.gameLibrary.controller.converter;

import com.example.gameLibrary.application.LibraryService;
import com.example.gameLibrary.application.LibraryServiceInterface;
import com.example.gameLibrary.controller.dto.LibraryDTO;
import com.example.gameLibrary.controller.dto.LibraryUserDTO;
import com.example.gameLibrary.model.LibraryUser;
import org.springframework.stereotype.Component;

@Component
public class LibraryUserDTOConverter implements DTOConverter<LibraryUserDTO, LibraryUser> {
    LibraryServiceInterface libraryService;
    LibraryUserDTOConverter(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @Override
    public LibraryUserDTO toDTO(LibraryUser libraryUser) {
        return new LibraryUserDTO(libraryUser.getId(), libraryUser.getUsername(), libraryUser.getPassword(),
                libraryUser.getName(), libraryUser.getSurname(), libraryUser.getRegistrationDate(),
                libraryUser.getUserLibrary().getId());
    }

    @Override
    public LibraryUser toEntity(LibraryUserDTO libraryUserDTO) {
        if (libraryUserDTO.getOwnedLibraryId() != null) {
            return new LibraryUser(libraryUserDTO.getId(), libraryUserDTO.getUsername(), libraryUserDTO.getPassword(),
                    libraryUserDTO.getName(), libraryUserDTO.getSurname(), libraryUserDTO.getRegistrationDate(),
                    libraryService.getLibraryById(libraryUserDTO.getOwnedLibraryId()));
        }
        return new LibraryUser(libraryUserDTO.getId(), libraryUserDTO.getUsername(), libraryUserDTO.getPassword(),
                libraryUserDTO.getName(), libraryUserDTO.getSurname(), libraryUserDTO.getRegistrationDate(),
                null);

    }
}
