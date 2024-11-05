package com.example.histomap.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonAppend;

public class Point extends GeoJsonFeature {

    public Point( float x, float y ) {
        this.geometry = new PointGeometry( x, y );
    }

}
