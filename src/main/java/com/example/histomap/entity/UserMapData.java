package com.example.histomap.entity;

import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.ArrayList;

public class UserMapData {
    ObjectNode geoJson;

    public ObjectNode getGeoJson() {
        return geoJson;
    }

    public void setGeoJson(ObjectNode geoJson) {
        this.geoJson = geoJson;
    }

    public ArrayList<UserMapDataAnnotation> getAnnotations() {
        return annotations;
    }

    public void setAnnotations(ArrayList<UserMapDataAnnotation> annotations) {
        this.annotations = annotations;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }


    ArrayList<UserMapDataAnnotation> annotations;
    int year;
}
