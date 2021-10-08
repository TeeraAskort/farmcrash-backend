package com.alderaeney.farmcrashbackend.task.exceptions;

public class TaskNotFoundException extends RuntimeException {
    private static final Long serialVersionUID = 4L;
    public Long id;

    public TaskNotFoundException(Long id) {
        this.id = id;
    }

}
