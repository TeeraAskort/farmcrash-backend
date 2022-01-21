package com.alderaeney.farmcrashbackend.player;

import java.math.BigInteger;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerListEntry {
    private Long id;
    private String name;
    private BigInteger money;
    private String image;
}
