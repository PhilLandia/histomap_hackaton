package com.example.histomap.controllers;

import com.example.histomap.entity.Point;
import com.example.histomap.storage.PointStorage;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PointController {

    @Autowired PointStorage storage;
    @GetMapping("/")
    public String index() {
        return "Hi!";
    }

    @GetMapping("/point/{id}")
    public Point point(@PathVariable Integer id) {
        return storage.points.get( id );
    }

}
