package com.example.histomap.storage;

import com.example.histomap.entity.Point;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class PointStorage {
    public HashMap<Integer, Point> points = new HashMap<>();

    @PostConstruct
    public void init() {
        points.put( 0, new Point( 32, 45 ) );
        points.put( 1, new Point( -3, 45.2f ) );
    }
}
