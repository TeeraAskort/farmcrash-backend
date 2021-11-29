package com.alderaeney.farmcrashbackend.crop.exceptions;

public class CropNotFarmeableException extends RuntimeException {
    public Long id;

    public CropNotFarmeableException(Long id) {
        this.id = id;
    }
}
