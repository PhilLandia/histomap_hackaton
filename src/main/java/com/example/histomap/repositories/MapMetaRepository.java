package com.example.histomap.repositories;

import com.example.histomap.dbentities.MapMetaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MapMetaRepository extends JpaRepository<MapMetaEntity, Long> {
    List<MapMetaEntity> findByMapNames_Id(Long mapId);
}
