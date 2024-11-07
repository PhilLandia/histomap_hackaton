package com.example.histomap.repositories;

import com.example.histomap.dbentities.MapNameEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MapNameRepository extends JpaRepository<MapNameEntity, Long> {
}