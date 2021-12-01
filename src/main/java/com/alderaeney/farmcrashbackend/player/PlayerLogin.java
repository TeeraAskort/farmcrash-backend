package com.alderaeney.farmcrashbackend.player;

import lombok.Data;

@Data
public class PlayerLogin {
    private String name;
    private String password;
    private String passwordRepeat;
}
