package com.alderaeney.farmcrashbackend.crop.exceptions;

public class CropNotFoundException extends RuntimeException {
    public Long id;

    public CropNotFoundException(Long id) {
        this.id = id;
    }
}
