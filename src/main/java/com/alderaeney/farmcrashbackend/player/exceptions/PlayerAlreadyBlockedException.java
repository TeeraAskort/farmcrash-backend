package com.alderaeney.farmcrashbackend.player.exceptions;

public class PlayerAlreadyBlockedException extends RuntimeException {
    public String username;

    public PlayerAlreadyBlockedException(String username) {
        this.username = username;
    }
}
