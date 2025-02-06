package com.example.gameLibrary.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping(path = "/rest/api")
public class MainPageController {
    @GetMapping
    public String home() { return "/keko.html" ;}

    @GetMapping(path = "register")
    public String register() { return "/register.html";}
}
