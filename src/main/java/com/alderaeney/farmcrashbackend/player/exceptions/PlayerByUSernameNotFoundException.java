package com.alderaeney.farmcrashbackend.player.exceptions;

public class PlayerByUSernameNotFoundException extends RuntimeException {
    public String username;

    public PlayerByUSernameNotFoundException(String username) {
        this.username = username;
    }
}
