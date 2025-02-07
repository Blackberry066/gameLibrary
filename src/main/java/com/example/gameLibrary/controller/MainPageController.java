package com.example.gameLibrary.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping(path = "/rest/api")
public class MainPageController {
    @GetMapping
    public String home() { return "/index.html" ;}

    @GetMapping(path = "register")
    public String register() { return "/register.html";}

    @GetMapping(path = "user/{userId}")
    public String profile(@PathVariable Long userId) {return "/profile.html";}

    @GetMapping(path = "login")
    public String login() {return "/login.html";}

    @GetMapping(path = "intersections")
    public String intersections() {return "/intersection.html";}

}
