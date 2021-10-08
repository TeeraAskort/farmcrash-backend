package com.alderaeney.farmcrashbackend.task.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class TaskExceptionController {

    @ExceptionHandler(value = TaskNotFoundException.class)
    public ResponseEntity<Object> taskNotFoundException(TaskNotFoundException exception) {
        return new ResponseEntity<>("Task with id " + exception.id + " was not found", HttpStatus.NOT_FOUND);
    }

}
