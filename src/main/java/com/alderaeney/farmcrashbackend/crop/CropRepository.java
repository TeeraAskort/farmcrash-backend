package com.alderaeney.farmcrashbackend.crop;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CropRepository extends JpaRepository<Crop, Long> {
    Optional<Crop> findByName(String name);

    Optional<List<Crop>> findAllByStage(CropStage stage);
}
