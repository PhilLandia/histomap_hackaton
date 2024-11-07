package com.example.histomap.services;

import com.example.histomap.dbentities.MapEntity;
import com.example.histomap.dtos.MapDto;
import com.example.histomap.repositories.MapRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MapService {

    private final MapRepository mapRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public MapService(MapRepository mapRepository) {
        this.mapRepository = mapRepository;
    }

    @Transactional
    public MapEntity saveMap(MapDto mapDto) {
        MapEntity map = new MapEntity();
        map.setName(mapDto.getName());
        map.setDescription(mapDto.getDescription());

        return mapRepository.save(map);
    }

    @Transactional(readOnly = true)
    public List<MapEntity> getAllMaps() {
        return entityManager
                .createQuery("SELECT * FROM maps m", MapEntity.class)
                .getResultList();
    }

    @Transactional(readOnly = true)
    public MapDto getMapById(Long id) {
        MapEntity mapEntity = mapRepository
                .findById(id)
                .orElse(null);

        if (mapEntity != null) {
            return new MapDto(mapEntity.getId(), mapEntity.getName(), mapEntity.getDescription());
        }
        return null;
    }
}
