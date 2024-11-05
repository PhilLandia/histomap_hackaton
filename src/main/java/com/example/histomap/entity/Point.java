package com.example.histomap.entity;


public class Point extends GeoJsonFeatureImpl {

    public Point( float x, float y ) {
        this.geometry = new PointGeometry( x, y );
    }

}
