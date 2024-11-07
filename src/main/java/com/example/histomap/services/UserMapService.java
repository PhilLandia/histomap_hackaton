package com.example.histomap.services;

import com.example.histomap.entity.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;

@Service
public class UserMapService {
    public MapName getMapName(UserMapEntity userMap) {
        return new MapName( 0, userMap.getName() );
    }

    public MapMeta getMapMeta(UserMapEntity userMap) {
        return new MapMeta( userMap.getMeta().start(), userMap.getMeta().end(), userMap.getMeta().step() );
    }

    public ArrayList<ObjectNode> getGeoJsons(UserMapEntity userMap) {
        ArrayList<ObjectNode> geoJsons = new ArrayList<>();



        userMap.getData().forEach( userMapData -> {
            ObjectNode geoJson = userMapData.getGeoJson();
            GeoJsonProperty property = new GeoJsonProperty( userMap.getName(), userMapData.getYear() );
            ObjectMapper mapper = new ObjectMapper();
            JsonNode propertyNode = mapper.valueToTree( property );
            geoJson.set( "property", propertyNode );

            userMapData.getAnnotations().forEach( userMapDataAnnotation -> {
                Point annotationPoint = new Point( userMapDataAnnotation.getCoordinates()[0], userMapDataAnnotation.getCoordinates()[1] );
                HashMap<String, String> annotationProperty = new HashMap<>();
                annotationProperty.put( "text", userMapDataAnnotation.getDescription() );
                annotationPoint.setProperty( annotationProperty );
                geoJsons.add( mapper.valueToTree( annotationPoint ) );
            } );


            geoJsons.add( geoJson );
        } );

        geoJsons.forEach(System.out::println);

        return geoJsons;
    }
}
