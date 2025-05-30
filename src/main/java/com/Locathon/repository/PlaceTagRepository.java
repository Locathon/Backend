package com.Locathon.repository;

import com.Locathon.model.PlaceTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaceTagRepository extends JpaRepository<PlaceTag, Long> {
    List<PlaceTag> findByPlaceId(Long placeId);
}

