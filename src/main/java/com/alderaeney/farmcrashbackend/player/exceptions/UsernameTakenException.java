package com.alderaeney.farmcrashbackend.player.exceptions;


public class UsernameTakenException extends RuntimeException {
    public String name;

    public UsernameTakenException(String name) {
        this.name = name;
    }
}
