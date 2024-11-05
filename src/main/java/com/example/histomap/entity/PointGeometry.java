package com.example.histomap.entity;

public class PointGeometry implements GeoJsonGeometry {
    static String type = "Point";
    float[] coordinates = new float[2];

    public PointGeometry( float x, float y ) {
        coordinates[0] = x;
        coordinates[1] = y;
    }

    @Override
    public float[] getCoordinates() {
        return coordinates;
    }

    @Override
    public String getType() {
        return type;
    }
}
