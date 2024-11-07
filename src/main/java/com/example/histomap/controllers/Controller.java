package com.example.histomap.controllers;

import com.example.histomap.entity.MapMeta;
import com.example.histomap.entity.MapName;
import com.example.histomap.entity.Point;
import com.example.histomap.entity.UserMapEntity;
import com.example.histomap.services.UserMapService;
import com.example.histomap.storage.PointStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class Controller {

    final PointStorage storage;
    final UserMapService userMapService;

    @Autowired
    public Controller(PointStorage storage, UserMapService userMapService) {
        this.storage = storage;
        this.userMapService = userMapService;
    }

    @GetMapping("/")
    public String index() {
        return "Hi!";
    }

    @GetMapping("/point/{id}")
    public Point point(@PathVariable Integer id) {
        return storage.points.get( id );
    }

    @PostMapping(path = "/createMap")
    public void createMap(@RequestBody UserMapEntity userMap) {
        MapName mapName = userMapService.getMapName ( userMap );
        MapMeta mapMeta = userMapService.getMapMeta( userMap );
        userMapService.getGeoJsons( userMap );
        System.out.println( mapName );
    }

}
