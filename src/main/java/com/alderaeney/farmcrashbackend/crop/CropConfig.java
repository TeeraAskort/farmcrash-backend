package com.alderaeney.farmcrashbackend.crop;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alderaeney.farmcrashbackend.crop.Crop;

@Configuration
public class CropConfig {

    @Bean
    CommandLineRunner cropConfiguration(CropRepository repository) {
        return args -> {
            Crop carrot = new Crop(CropStage.BUY, "Carrot", 100, 75, CropType.NOTREUSABLE, "carrot");
            Crop daikon = new Crop(CropStage.BUY, "Daikon", 175, 100, CropType.NOTREUSABLE, "daikon");
            Crop europeanRadish = new Crop(CropStage.BUY, "European Radish", 150, 75, CropType.NOTREUSABLE,
                    "european-radish");
            Crop onion = new Crop(CropStage.BUY, "Onion", 125, 45, CropType.NOTREUSABLE, "onion");
            Crop potato = new Crop(CropStage.BUY, "Potato", 125, 30, CropType.NOTREUSABLE, "potato");
            Crop purplePotato = new Crop(CropStage.BUY, "Purple Potato", 150, 65, CropType.NOTREUSABLE,
                    "purple-potato");
            Crop redOnion = new Crop(CropStage.BUY, "Red Onion", 175, 100, CropType.NOTREUSABLE, "red-onion");
            Crop redPotato = new Crop(CropStage.BUY, "Red Potato", 200, 125, CropType.NOTREUSABLE, "red-potato");
            Crop scallion = new Crop(CropStage.BUY, "Scallion", 100, 25, CropType.NOTREUSABLE, "scallion");
            Crop shallot = new Crop(CropStage.BUY, "Shallot", 75, 20, CropType.NOTREUSABLE, "shallot");
            Crop sweetPotato = new Crop(CropStage.BUY, "Sweet Potato", 150, 90, CropType.NOTREUSABLE, "sweet-potato");
            Crop turnip = new Crop(CropStage.BUY, "Turnip", 145, 70, CropType.NOTREUSABLE, "turnip");
            Crop whiteCarrot = new Crop(CropStage.BUY, "White Carrot", 200, 100, CropType.NOTREUSABLE, "white-carrot");

            repository.saveAll(List.of(carrot, daikon, europeanRadish, onion, potato, purplePotato, redOnion, redPotato,
                    scallion, shallot, sweetPotato, turnip, whiteCarrot));
        };
    }

}
