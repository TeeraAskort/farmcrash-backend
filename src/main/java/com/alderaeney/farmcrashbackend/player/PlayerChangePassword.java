package com.alderaeney.farmcrashbackend.player;

import lombok.Data;

@Data
public class PlayerChangePassword {
    private String oldPass;
    private String newPass;
    private String newPassRepeat;
}
