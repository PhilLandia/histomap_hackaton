package com.example.histomap.services;

import com.example.histomap.dtos.MapNameDto;
import com.example.histomap.dtos.MapMetaDto;
import com.example.histomap.entity.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;

@Service
public class InputMapService {
    public MapNameDto getMapName(UserMapDto userMap) {
        return new MapNameDto(userMap.getName());
    }

    public MapMetaDto getMapMeta(UserMapDto userMap) {
        return new MapMetaDto(userMap.getMeta().start(), userMap.getMeta().end(), userMap.getMeta().step());
    }

    public ArrayList<ObjectNode> getGeoJsons(UserMapDto userMap) {
        ArrayList<ObjectNode> geoJsons = new ArrayList<>();

        userMap.getData().forEach(userMapData -> {
            ObjectNode geoJson = userMapData.getGeoJson();
            GeoJsonProperty property = new GeoJsonProperty(userMap.getName(), userMapData.getYear());
            ObjectMapper mapper = new ObjectMapper();
            JsonNode propertiesNode = mapper.valueToTree(property);
            geoJson.set("properties", propertiesNode);

            userMapData.getAnnotations().forEach(userMapDataAnnotation -> {
                Point annotationPoint = new Point(userMapDataAnnotation.getCoordinates()[0], userMapDataAnnotation.getCoordinates()[1]);
                HashMap<String, Object> annotationProperty = new HashMap<>();
                annotationProperty.put("text", userMapDataAnnotation.getDescription());

                annotationProperty.put("mapName", property.mapName());
                annotationProperty.put("year", property.year());

                annotationPoint.setProperties(annotationProperty);
                geoJsons.add(mapper.valueToTree(annotationPoint));
            });


            geoJsons.add(geoJson);
        });

        geoJsons.forEach(System.out::println);

        return geoJsons;
    }

}
