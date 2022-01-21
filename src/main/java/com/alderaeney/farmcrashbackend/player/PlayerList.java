package com.alderaeney.farmcrashbackend.player;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerList {
    private Integer page;
    private List<PlayerListEntry> players;
}
