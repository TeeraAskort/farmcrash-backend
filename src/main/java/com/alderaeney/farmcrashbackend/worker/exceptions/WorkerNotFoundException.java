package com.alderaeney.farmcrashbackend.worker.exceptions;

public class WorkerNotFoundException extends RuntimeException {
    public Long id;

    public WorkerNotFoundException(Long id) {
        this.id = id;
    }
}
