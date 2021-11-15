package com.alderaeney.farmcrashbackend.player.exceptions;

public class IndexOutOfBoundsException extends RuntimeException {
    public int index;

    public IndexOutOfBoundsException(int index) {
        this.index = index;
    }
}
