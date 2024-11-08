package com.example.histomap.dbentities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "map_meta")
@Data
public class MapMetaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "start")
    private Integer start;

    @Column(name = "\"end\"")
    private Integer end;

    @Column(name = "step")
    private Integer step;

    @ManyToOne
    @JoinColumn(name = "map_id")
    private MapNameEntity mapNames;
}

