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

    public List<Crop> getAllCrops() {
        return repository.findAll();
    }

    public Optional<Crop> getCropById(Long id) {
        return repository.findById(id);
    }

}
