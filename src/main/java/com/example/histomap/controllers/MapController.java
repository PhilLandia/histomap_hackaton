package com.example.histomap.controllers;

import com.example.histomap.dbentities.MapMetaEntity;
import com.example.histomap.dbentities.MapNameEntity;
import com.example.histomap.dtos.MapNameDto;
import com.example.histomap.dtos.MapMetaDto;
import com.example.histomap.entity.UserMapDto;
import com.example.histomap.services.FeatureService;
import com.example.histomap.services.MapMetaService;
import com.example.histomap.services.MapNameService;
import com.example.histomap.services.InputMapService;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
public class MapController {
    private final InputMapService inputMapService;
    private final FeatureService featureService;
    private final MapNameService mapNameService;
    private final MapMetaService mapMetaService;

    @Autowired
    public MapController(
            InputMapService inputMapService,
            FeatureService featureService,
            MapNameService mapNameService,
            MapMetaService mapMetaService) {
        this.inputMapService = inputMapService;
        this.featureService = featureService;
        this.mapNameService = mapNameService;
        this.mapMetaService = mapMetaService;
    }

    @PostMapping(path = "/createMap")
    public void createMap(@RequestBody UserMapDto userMap) {
        MapNameDto mapNameDto = inputMapService.getMapName(userMap);
        MapNameEntity mapNameEntity = mapNameService.saveMap(mapNameDto);

        MapMetaDto mapMetaDto = inputMapService.getMapMeta(userMap);
        MapMetaEntity mapMetaEntity = mapMetaService.saveMapMeta(mapMetaDto, mapNameEntity.getId());

        ArrayList<ObjectNode> geoJsons = inputMapService.getGeoJsons(userMap);
        featureService.saveFeature(mapNameEntity, mapMetaEntity, geoJsons);
    }

    @GetMapping("/ViewAllMaps")
    public List<MapNameEntity> getAllMaps() {
        return mapNameService.getAllMaps();
    }

    @GetMapping("/getMeta/{id}")
    public ResponseEntity<MapMetaEntity> getMeta(@PathVariable Long id) {
        MapMetaEntity mapMetaEntity = mapMetaService.getMapMetaByMapId(id);
        if (mapMetaEntity != null) {
            return ResponseEntity.ok(mapMetaEntity);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
