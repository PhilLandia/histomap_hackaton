package com.example.histomap.entity;

import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.ArrayList;

public class UserMapEntity {
    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public ArrayList<UserMapData> getData() {
        return data;
    }

    public void setData(ArrayList<UserMapData> data) {
        this.data = data;
    }

    public record Meta(int start, int end, int step) {
    }

    Meta meta;
    ArrayList<UserMapData> data;
}
