package com.example.histomap.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
public class Controller {
    @GetMapping("/")
    public String index() {
        return "Hi!";
    }
}
