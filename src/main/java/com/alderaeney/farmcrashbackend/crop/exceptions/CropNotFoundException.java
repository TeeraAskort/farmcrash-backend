package com.alderaeney.farmcrashbackend.crop.exceptions;

public class CropNotFoundException extends RuntimeException {
    private static final Long serialVersionUID = 2L;
    public Long id;

    public CropNotFoundException(Long id) {
        this.id = id;
    }
}
