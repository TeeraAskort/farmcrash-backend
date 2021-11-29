package com.alderaeney.farmcrashbackend.player.exceptions;

public class WorkerNotFoundInPlayerException extends RuntimeException {
    public Integer index;

    public WorkerNotFoundInPlayerException(Integer index) {
        this.index = index;
    }
}
