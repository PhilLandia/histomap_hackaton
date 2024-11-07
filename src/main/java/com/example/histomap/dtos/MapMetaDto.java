package com.example.histomap.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MapMetaDto {
    private Integer start;
    private Integer end;
    private Integer step;
}
