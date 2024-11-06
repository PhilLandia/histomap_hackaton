package com.example.histomap.services;

import com.example.histomap.entity.MapMeta;
import com.example.histomap.entity.MapName;
import com.example.histomap.entity.UserMapEntity;
import org.springframework.stereotype.Service;

@Service
public class UserMapService {
    public MapName getMapName(UserMapEntity userMap) {
        return new MapName( 0, userMap.getName() );
    }

    public MapMeta getMapMeta(UserMapEntity userMap) {
        return new MapMeta( userMap.getMeta().start(), userMap.getMeta().end(), userMap.getMeta().step() );
    }
}
