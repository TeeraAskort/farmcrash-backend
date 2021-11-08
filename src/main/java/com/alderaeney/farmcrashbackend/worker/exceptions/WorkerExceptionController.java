package com.alderaeney.farmcrashbackend.worker.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class WorkerExceptionController {
    @ExceptionHandler(value = WorkerNotFoundException.class)
    public ResponseEntity<Object> workerNotFoundException(WorkerNotFoundException exception) {
        return new ResponseEntity<>("Worker number " + exception.id + " was not found", HttpStatus.NOT_FOUND);
    }
}
