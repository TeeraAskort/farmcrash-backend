package com.alderaeney.farmcrashbackend.player.exceptions;

public class PlayerNotBlockedException extends RuntimeException {
    public String username;

    public PlayerNotBlockedException(String username) {
        this.username = username;
    }
}
