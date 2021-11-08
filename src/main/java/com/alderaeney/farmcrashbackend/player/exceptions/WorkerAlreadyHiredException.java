package com.alderaeney.farmcrashbackend.player.exceptions;

public class WorkerAlreadyHiredException extends RuntimeException {
    public Long id;

    public WorkerAlreadyHiredException(Long id) {
        this.id = id;
    }
}
