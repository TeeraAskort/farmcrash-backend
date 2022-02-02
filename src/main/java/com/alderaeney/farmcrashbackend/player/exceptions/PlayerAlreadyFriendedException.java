package com.alderaeney.farmcrashbackend.player.exceptions;

public class PlayerAlreadyFriendedException extends RuntimeException {
    public String name;

    public PlayerAlreadyFriendedException(String name) {
        this.name = name;
    }
}
