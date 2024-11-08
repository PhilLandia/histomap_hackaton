package com.example.histomap.services;

import com.example.histomap.dbentities.MapMetaEntity;
import com.example.histomap.dbentities.MapNameEntity;
import com.example.histomap.dtos.MapMetaDto;
import com.example.histomap.repositories.MapMetaRepository;
import com.example.histomap.repositories.MapNameRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MapMetaService {

    private final MapNameRepository mapNameRepository;
    private final MapMetaRepository mapMetaRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public MapMetaService(MapNameRepository mapNameRepository, MapMetaRepository mapMetaRepository) {
        this.mapNameRepository = mapNameRepository;
        this.mapMetaRepository = mapMetaRepository;
    }

    @Transactional
    public MapMetaEntity saveMapMeta(MapMetaDto mapMetaDto, Long mapNameId) {
        MapNameEntity mapNames = mapNameRepository
                .findById(mapNameId)
                .orElseThrow(() -> new IllegalArgumentException("Map not found"));

        MapMetaEntity mapMeta = new MapMetaEntity();
        mapMeta.setStart(mapMetaDto.getStart());
        mapMeta.setEnd(mapMetaDto.getEnd());
        mapMeta.setStep(mapMetaDto.getStep());
        mapMeta.setMapNames(mapNames);

        return mapMetaRepository.save(mapMeta);
    }

    @Transactional(readOnly = true)
    public MapMetaEntity getMapMetaByMapId(Long mapNamesId) {
        List<MapMetaEntity> mapMetaEntityList = mapMetaRepository.findByMapNames_Id(mapNamesId);

        if (!mapMetaEntityList.isEmpty()) {
            return mapMetaEntityList.getFirst();
        }

        return null;
    }
}
