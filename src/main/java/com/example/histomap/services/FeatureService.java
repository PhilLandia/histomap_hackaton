package com.example.histomap.services;

import com.example.histomap.dbentities.FeatureEntity;
import com.example.histomap.dbentities.MapEntity;
import com.example.histomap.dtos.FeatureDto;
import com.example.histomap.repositories.FeatureRepository;
import com.example.histomap.repositories.MapRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FeatureService {

    private final FeatureRepository featureRepository;
    private final MapRepository mapRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public FeatureService(FeatureRepository featureRepository, MapRepository mapRepository) {
        this.featureRepository = featureRepository;
        this.mapRepository = mapRepository;
    }

    @Transactional
    public FeatureEntity saveFeature(FeatureDto featureDto) {
        MapEntity map = mapRepository
                .findById(featureDto.getMapId())
                .orElseThrow(() -> new IllegalArgumentException("Map not found"));

        FeatureEntity feature = new FeatureEntity();
        feature.setYear(featureDto.getYear());
        feature.setAnnotation(featureDto.getAnnotation());
        feature.setColor(featureDto.getColor());
        feature.setGeometry(featureDto.getGeometry());
        feature.setMap(map);

        return featureRepository.save(feature);
    }
}
