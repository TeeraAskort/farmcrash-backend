package com.alderaeney.farmcrashbackend.player.exceptions;

public class ImageTypeNotSupported extends RuntimeException {
    String msg = "Image type not supported, supported types are .jpg, .jpeg, .png and .gif";
}
