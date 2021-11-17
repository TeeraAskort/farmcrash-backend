package com.alderaeney.farmcrashbackend.player.exceptions;

public class NotEnoughMoneyToPerformTaskException extends RuntimeException {
    public Integer money;
    public Integer cost;

    public NotEnoughMoneyToPerformTaskException(Integer money, Integer cost) {
        this.money = money;
        this.cost = cost;
    }
}
