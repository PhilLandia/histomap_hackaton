package com.example.histomap.dbentities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "map_names")
@Data
public class MapNameEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 255)
    private String name;
}