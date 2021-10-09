package com.alderaeney.farmcrashbackend.player.exceptions;

import java.math.BigInteger;

public class NotEnoughMoneyException extends RuntimeException {
    public Integer price;
    public BigInteger availableMoney;

    public NotEnoughMoneyException(Integer price, BigInteger availableMoney) {
        this.price = price;
        this.availableMoney = availableMoney;
    }
}
