package com.example.histomap.services;

import com.example.histomap.dbentities.MapMetaEntity;
import com.example.histomap.dbentities.MapNameEntity;
import com.example.histomap.repositories.MapNameRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

@Service
public class FeatureService {
    private final String sqlLayer1 =
            "INSERT INTO layer1 (geom, note, year, color, map_id) " +
                    "VALUES (ST_GeomFromGeoJSON(:geoJson), :note, :year, :color, :mapId)";

    private final String sqlLayer2 =
            "INSERT INTO layer2 (geom, note, year, color, map_id) " +
                    "VALUES (ST_GeomFromGeoJSON(:geoJson), :note, :year, :color, :mapId)";

    private final MapNameRepository mapNameRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public FeatureService(MapNameRepository mapNameRepository) {
        this.mapNameRepository = mapNameRepository;
    }

    @Transactional
    public void saveFeature(MapNameEntity mapNameEntity, MapMetaEntity mapMetaEntity, ArrayList<ObjectNode> geoJsons) {
        MapNameEntity map = mapNameRepository
                .findById(mapNameEntity.getId())
                .orElseThrow(() -> new IllegalArgumentException("Map not found"));

        geoJsons.forEach(geoJson -> {
            JsonNode geoJsonGeometry = geoJson.get("geometry");
            JsonNode properties = geoJson.get("properties");

            int year = properties.get("year").asInt();
            String text = null;
            String geometryType = geoJsonGeometry.get("type").asText();

            if (geometryType.equals("Point")) {
                text = properties.get("text").asText();
            }

            Random random = new Random();
            Color color = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
            String hex = "#"+Integer.toHexString(color.getRGB()).substring(2).toUpperCase();

            entityManager
                    .createNativeQuery(
                            geometryType.equals("Point") ?
                                    sqlLayer2 :
                                    sqlLayer1
                    )
                    .setParameter("geoJson", geoJsonGeometry.toString())
                    .setParameter("note", text)
                    .setParameter("year", year)
                    .setParameter("color", hex)
                    .setParameter("mapId", map.getId())
                    .executeUpdate();
        });
    }
}
