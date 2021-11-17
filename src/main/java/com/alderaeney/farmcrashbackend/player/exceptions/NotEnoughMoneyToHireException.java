package com.alderaeney.farmcrashbackend.player.exceptions;

public class NotEnoughMoneyToHireException extends RuntimeException {
    public Integer money;
    public Integer cost;

    public NotEnoughMoneyToHireException(Integer money, Integer cost) {
        this.money = money;
        this.cost = cost;
    }
}
