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
            Crop melon = new Crop(CropStage.BUY, "Melon", 250, 100, CropType.REUSABLE);
            Crop turnip = new Crop(CropStage.BUY, "Turnip", 100, 175, CropType.REUSABLE);

            repository.saveAll(List.of(melon, turnip));
        };
    }

}
