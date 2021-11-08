package com.alderaeney.farmcrashbackend.crop.exceptions;

public class CropNotFarmeableException extends RuntimeException {
    private static final Long serialVersionUID = 5L;
    public Long id;

    public CropNotFarmeableException(Long id) {
        this.id = id;
    }
}
