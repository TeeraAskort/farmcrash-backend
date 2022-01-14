package com.alderaeney.farmcrashbackend.player.exceptions;

public class ImageTooBigException extends RuntimeException {
    String msg = "Image too big, maximum allowed size of 1MB";
}
