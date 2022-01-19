package com.alderaeney.farmcrashbackend.player.exceptions;

public class OldPasswordDoesNotMatchException extends RuntimeException {
    public String msg = "Old password doesn't match our stored password";
}
