package com.example.histomap.dbentities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "maps")
@Data
public class MapEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;
}