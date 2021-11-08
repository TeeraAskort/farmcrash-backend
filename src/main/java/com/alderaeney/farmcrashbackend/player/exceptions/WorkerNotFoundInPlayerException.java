package com.alderaeney.farmcrashbackend.player.exceptions;

public class WorkerNotFoundInPlayerException extends RuntimeException {
    private static final Long serialVersionUID = 7L;
    public Integer index;

    public WorkerNotFoundInPlayerException(Integer index) {
        this.index = index;
    }
}
