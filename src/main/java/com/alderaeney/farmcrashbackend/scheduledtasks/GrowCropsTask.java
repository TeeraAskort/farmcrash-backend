package com.alderaeney.farmcrashbackend.scheduledtasks;

import java.util.List;

import com.alderaeney.farmcrashbackend.crop.Crop;
import com.alderaeney.farmcrashbackend.crop.CropStage;
import com.alderaeney.farmcrashbackend.player.Player;
import com.alderaeney.farmcrashbackend.player.PlayerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class GrowCropsTask {
    private final PlayerService playerService;

    @Autowired
    public GrowCropsTask(PlayerService playerService) {
        this.playerService = playerService;
    }

    @Scheduled(fixedRate = 10000)
    public void growCrops() {
        List<Player> players = playerService.getAllPlayers();
        for (Player player : players) {
            for (Crop crop : player.getCrops()) {
                if (crop.getStage() == CropStage.DAY0) {
                    crop.setStage(CropStage.DAY1);
                } else if (crop.getStage() == CropStage.DAY1) {
                    crop.setStage(CropStage.DAY2);
                } else if (crop.getStage() == CropStage.DAY2) {
                    crop.setStage(CropStage.DAY3);
                } else if (crop.getStage() == CropStage.DAY3) {
                    crop.setStage(CropStage.READYTOFARM);
                }
            }
        }
    }
}
