package com.example.histomap.controllers;

import com.example.histomap.dtos.MapDto;
import com.example.histomap.dtos.MapMetaDto;
import com.example.histomap.entity.Point;
import com.example.histomap.entity.UserMapEntity;
import com.example.histomap.services.MapService;
import com.example.histomap.services.UserMapService;
import com.example.histomap.storage.PointStorage;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class Controller {

    final PointStorage storage;
    final UserMapService userMapService;
    final MapService mapService;

    @Autowired
    public Controller(PointStorage storage, UserMapService userMapService, MapService mapService) {
        this.storage = storage;
        this.userMapService = userMapService;
        this.mapService = mapService;
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
        MapDto mapDto = userMapService.getMapName ( userMap );
        mapService.saveMap( mapDto );

        MapMetaDto mapMeta = userMapService.getMapMeta( userMap );
        ArrayList<ObjectNode> geoJsons = userMapService.getGeoJsons( userMap );
    }

}
