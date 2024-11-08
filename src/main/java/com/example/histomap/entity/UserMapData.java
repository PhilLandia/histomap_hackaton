package com.example.histomap.entity;

import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Setter
@Getter
public class UserMapData {
    ObjectNode geoJson;

    ArrayList<UserMapDataAnnotation> annotations;
    int year;
}
