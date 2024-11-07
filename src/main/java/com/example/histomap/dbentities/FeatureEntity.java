package com.example.histomap.dbentities;

import jakarta.persistence.*;
import lombok.Data;
import org.locationtech.jts.geom.MultiPolygon;

@Entity
@Table(name = "features")
@Data
public class FeatureEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer year;

    @Column(columnDefinition = "TEXT")
    private String annotation;

    @Column(length = 7)
    private String color;

    @Column(columnDefinition = "geometry(MultiPolygon, 4326)")
    private MultiPolygon geometry;

    @ManyToOne
    @JoinColumn(name = "map_id", referencedColumnName = "id")
    private MapEntity map;
}