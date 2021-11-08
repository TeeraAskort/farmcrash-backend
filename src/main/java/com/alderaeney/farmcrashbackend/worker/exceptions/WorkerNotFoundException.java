package com.alderaeney.farmcrashbackend.worker.exceptions;

public class WorkerNotFoundException extends RuntimeException {
    private static final Long serialVersionUID = 3L;
    public Long id;

    public WorkerNotFoundException(Long id) {
        this.id = id;
    }
}
