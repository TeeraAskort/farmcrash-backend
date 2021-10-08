package com.alderaeney.farmcrashbackend.crop.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CropExceptionController {
    @ExceptionHandler(value = CropNotFoundException.class)
    public ResponseEntity<Object> cropNotFoundException(CropNotFoundException exception) {
        return new ResponseEntity<Object>("Crop with id " + exception.id + " doesn't exist", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = CropNotFarmeableException.class)
    public ResponseEntity<Object> cropNotFarmeableException(CropNotFarmeableException exception) {
        return new ResponseEntity<>("Crop with id " + exception.id + " isn't farmeable", HttpStatus.NOT_ACCEPTABLE);
    }
}
