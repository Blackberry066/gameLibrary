package com.example.gameLibrary.controller;

import com.example.gameLibrary.repository.LibraryUserRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LibraryUserController {
    private LibraryUserRepository userRepository;

    @RequestMapping("/user")
    public String userHome() {
        return "index";
    }
}
