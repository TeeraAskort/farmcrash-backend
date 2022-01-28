package com.alderaeney.farmcrashbackend.player.exceptions;

public class FriendRequestToSamePlayerException extends RuntimeException {
    public final String username;

    public FriendRequestToSamePlayerException(String username) {
        this.username = username;
    }
}
