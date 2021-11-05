package com.alderaeney.farmcrashbackend.crop.exceptions;

public class CropNotFarmeableException extends RuntimeException {
    private static final Long serialVersionUID = 5L;
    public Crop crop

    public CropNotFarmeableException(Crop crop) {
        this.crop = crop;
    }
}
