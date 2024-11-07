package com.example.histomap.services;

import com.example.histomap.dbentities.MapNameEntity;
import com.example.histomap.dtos.MapNameDto;
import com.example.histomap.repositories.MapNameRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MapNameService {

    private final MapNameRepository mapNameRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public MapNameService(MapNameRepository mapNameRepository) {
        this.mapNameRepository = mapNameRepository;
    }

    @Transactional
    public MapNameEntity saveMap(MapNameDto mapNameDto) {
        MapNameEntity map = new MapNameEntity();
        map.setName(mapNameDto.getName());

        return mapNameRepository.save(map);
    }

    @Transactional(readOnly = true)
    public List<MapNameEntity> getAllMaps() {
        return entityManager
                .createQuery("SELECT m FROM MapNameEntity m", MapNameEntity.class)
                .getResultList();
    }

    @Transactional(readOnly = true)
    public MapNameEntity getMapById(Long id) {

        return mapNameRepository
                .findById(id)
                .orElse(null);
    }
}
