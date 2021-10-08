package com.alderaeney.farmcrashbackend.player.exceptions;

public class PlayerNotFoundException extends RuntimeException {
    private static final Long serialVersionUID = 1L;
    public Long id;

    public PlayerNotFoundException(Long id) {
        this.id = id;
    }
}
