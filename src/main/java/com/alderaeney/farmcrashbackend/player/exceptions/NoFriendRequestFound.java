package com.alderaeney.farmcrashbackend.player.exceptions;

public class NoFriendRequestFound extends RuntimeException {
    public String senderName;
    public String getterName;

    public NoFriendRequestFound(String senderName, String getterName) {
        this.senderName = senderName;
        this.getterName = getterName;
    }
}
