package com.alderaeney.farmcrashbackend.crop;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CropService {

    private final CropRepository repository;

    @Autowired
    public CropService(CropRepository repository) {
        this.repository = repository;
    }

    public List<Crop> getAllByStageCrops(CropStage stage) {
        Optional<List<Crop>> crops = repository.findAllByStage(stage);
        if (crops.isPresent()) {
            return crops.get();
        } else {
            return null;
        }
    }

    public Optional<Crop> getCropById(Long id) {
        return repository.findById(id);
    }

    public void addCrop(Crop crop) {
        repository.save(crop);
    }

    public void removeCrop(Long id) {
        repository.deleteById(id);
    }

}
