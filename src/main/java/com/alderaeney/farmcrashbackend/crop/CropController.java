package com.alderaeney.farmcrashbackend.crop;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/crop")
public class CropController {

    private final CropService service;

    @Autowired
    public CropController(CropService service) {
        this.service = service;
    }

    @GetMapping(path = "all")
    public List<Crop> getAllCrops() {
        return service.getAllCrops();
    }

    @GetMapping(path = "{cropId}")
    public Crop getCropById(@PathVariable("cropId") Long id) {
        Optional<Crop> crop = service.getCropById(id);
        if (crop.isPresent()) {
            return crop.get();
        }
        return null;
    }

}
