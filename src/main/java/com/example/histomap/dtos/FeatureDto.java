package com.example.histomap.dtos;

import lombok.Data;
import org.locationtech.jts.geom.MultiPolygon;

@Data
public class FeatureDto {
    private Long id;
    private Integer year;
    private String annotation;
    private String color;
    private MultiPolygon geometry;
    private Long mapId;
}