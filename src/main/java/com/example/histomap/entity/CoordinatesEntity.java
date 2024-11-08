package com.example.histomap.entity;

import com.example.histomap.utils.CoordinatesSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(using = CoordinatesSerializer.class)
public abstract class CoordinatesEntity {

}

