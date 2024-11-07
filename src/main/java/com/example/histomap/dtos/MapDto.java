package com.example.histomap.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MapDto {
    private Long id;
    private String name;
    private String description;
}