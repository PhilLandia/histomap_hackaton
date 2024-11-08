package com.example.histomap.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Setter
@Getter
public class UserMapDto {
    String name;

    public record Meta(int start, int end, int step) {
    }

    Meta meta;
    ArrayList<UserMapData> data;
}
