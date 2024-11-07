package com.example.histomap.entity;


import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

@Setter
@Getter
public class Point extends GeoJsonFeature {

    HashMap<String, Object> properties;

    public Point(double x, double y) {
        this.geometry = new PointGeometry(x, y);
    }
}
