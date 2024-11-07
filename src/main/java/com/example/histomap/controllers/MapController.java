package com.example.histomap.controllers;

import com.example.histomap.dbentities.MapEntity;
import com.example.histomap.dtos.MapDto;
import com.example.histomap.services.MapService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public class MapController {
    private final MapService mapService;

    public MapController(MapService mapService) {
        this.mapService = mapService;
    }

    @GetMapping("/ViewAllMaps")
    public List<MapEntity> getAllMaps() {
        return mapService.getAllMaps();
    }

    @GetMapping("/getMeta")
    public ResponseEntity<MapDto> getMeta(@RequestParam Long id) {
        MapDto mapDto = mapService.getMapById(id);
        if (mapDto != null) {
            return ResponseEntity.ok(mapDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
