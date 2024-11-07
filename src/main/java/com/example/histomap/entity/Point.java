package com.example.histomap.entity;


import java.util.HashMap;

public class Point extends GeoJsonFeatureImpl {

    HashMap<String, String> property;
    public Point( double x, double y ) {
        this.geometry = new PointGeometry( x, y );
    }

    public HashMap<String, String> getProperty() {
        return property;
    }

    public void setProperty(HashMap<String, String> property) {
        this.property = property;
    }
}
