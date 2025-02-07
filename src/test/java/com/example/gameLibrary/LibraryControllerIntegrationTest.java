package com.example.gameLibrary;

import com.example.gameLibrary.application.LibraryService;
import com.example.gameLibrary.model.Game;
import com.example.gameLibrary.model.Library;
import com.example.gameLibrary.model.LibraryUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class LibraryControllerIntegrationTest {
    @MockitoBean
    LibraryService libraryService;

    @Autowired
    MockMvc mockMvc;


    @Test
    public void testGetLibraries() throws Exception {
        when(libraryService.getAllLibraries()).thenReturn(
                List.of(
                        new Library(1L, 1, List.of(), new LibraryUser()),
                        new Library(2L, 2, List.of(), new LibraryUser())
                )
        );

        mockMvc.perform(get("/rest/api/libraries")
                        .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(jsonPath("$[1].gameCount").value(2));
    }

    @Test
    public void testGetLibraryById() throws Exception {
        Library someLibrary = new Library(1L, 122, List.of(), new LibraryUser());

        when(libraryService.getLibraryById(12L)).thenReturn(someLibrary);

        mockMvc.perform(get("/rest/api/libraries/"+12L)).andExpect(status().isOk());

    }


}
