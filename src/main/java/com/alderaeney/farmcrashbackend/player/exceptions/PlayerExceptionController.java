package com.alderaeney.farmcrashbackend.player.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class PlayerExceptionController {
    @ExceptionHandler(value = PlayerNotFoundException.class)
    public ResponseEntity<Object> playerNotFoundException(PlayerNotFoundException exception) {
        return new ResponseEntity<>("Player with id " + exception.id + " was not found in the database",
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = NotEnoughMoneyException.class)
    public ResponseEntity<Object> notEnoughMoneyException(NotEnoughMoneyException exception) {
        return new ResponseEntity<>("Not enough money to buy the crops<br>Available money: " + exception.availableMoney
                + "<br>Price to buy the crops: " + exception.price, HttpStatus.BAD_REQUEST);
    }
}
