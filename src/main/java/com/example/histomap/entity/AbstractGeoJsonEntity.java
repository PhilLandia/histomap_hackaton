package com.example.histomap.entity;

public class AbstractGeoJsonEntity implements GeoJsonEntity {
    GeoJsonGeometry geometry;
    String type;

    @Override
    public GeoJsonGeometry getGeometry() {
        return geometry;
    }

    @Override
    public String getType() {
        return type;
    }
}
