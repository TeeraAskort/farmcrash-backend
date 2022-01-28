package com.alderaeney.farmcrashbackend.player.exceptions;

public class PlayerBlockingItselfException extends RuntimeException {
    public String msg = "You're trying to block yourself";
}
