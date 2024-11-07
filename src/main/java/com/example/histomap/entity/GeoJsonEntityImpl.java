package com.example.histomap.entity;

import java.util.HashMap;

public class GeoJsonEntityImpl implements GeoJsonEntity {
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
