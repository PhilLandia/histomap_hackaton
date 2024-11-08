package com.example.histomap.dtos;

import lombok.Data;
import org.locationtech.jts.geom.MultiPolygon;

@Data
public class FeatureDto {
    private Long id;
    private Integer year;
    private String note;
    private String color;
    private MultiPolygon geometry;
    private Long mapId;
}