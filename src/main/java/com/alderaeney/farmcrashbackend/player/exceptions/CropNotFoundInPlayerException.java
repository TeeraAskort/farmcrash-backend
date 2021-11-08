package com.alderaeney.farmcrashbackend.player.exceptions;

public class CropNotFoundInPlayerException extends RuntimeException {

    public Integer index;

    public CropNotFoundInPlayerException(Integer index) {
        this.index = index;
    }

}
