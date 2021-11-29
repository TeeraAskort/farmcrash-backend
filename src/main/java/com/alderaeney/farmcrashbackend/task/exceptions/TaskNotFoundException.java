package com.alderaeney.farmcrashbackend.task.exceptions;

public class TaskNotFoundException extends RuntimeException {
    public Long id;

    public TaskNotFoundException(Long id) {
        this.id = id;
    }

}
