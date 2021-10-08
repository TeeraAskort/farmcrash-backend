package com.alderaeney.farmcrashbackend.item;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ItemConfig {

    @Bean
    CommandLineRunner itemConfiguration(ItemRepository repository) {
        return args -> {
            Item octopus = new Item("Octopus", ItemType.FISH, 500, "");
            Item star = new Item("Star", ItemType.FISH, 150, "");

            repository.saveAll(List.of(octopus, star));
        };
    }

}
