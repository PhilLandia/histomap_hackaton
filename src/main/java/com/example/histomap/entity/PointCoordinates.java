package com.example.histomap.entity;

public class PointCoordinates extends CoordinatesEntity {
    double[] coords;

    public PointCoordinates(double x, double y) {
        coords = new double[2];
        coords[0] = x;
        coords[1] = y;
    }

    public double[] getCoords() {
        return coords;
    }
}
