package com.alderaeney.farmcrashbackend.player.exceptions;

public class NotEnoughMoneyException extends RuntimeException {
    public Integer price;
    public Integer availableMoney;

    public NotEnoughMoneyException(Integer price, Integer availableMoney) {
        this.price = price;
        this.availableMoney = availableMoney;
    }
}
