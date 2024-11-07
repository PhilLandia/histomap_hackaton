package com.example.histomap.entity;

import lombok.Getter;

@Getter
public class PointCoordinates extends CoordinatesEntity {
    double[] coords;

    public PointCoordinates(double x, double y) {
        coords = new double[2];
        coords[0] = x;
        coords[1] = y;
    }

}
