package com.alderaeney.farmcrashbackend.player.exceptions;

public class PlayerNotFoundException extends RuntimeException {
    public Long id;

    public PlayerNotFoundException(Long id) {
        this.id = id;
    }
}
