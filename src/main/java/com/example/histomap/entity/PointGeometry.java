package com.example.histomap.entity;

public class PointGeometry implements GeoJsonGeometry {
    static String type = "Point";
    CoordinatesEntity coordinates;

    public PointGeometry( double x, double y ) {
        coordinates = new PointCoordinates( x, y );
    }

    @Override
    public CoordinatesEntity getCoordinates() {
        return coordinates;
    }

    @Override
    public String getType() {
        return type;
    }
}
