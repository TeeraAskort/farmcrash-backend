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
            Item octopus = new Item("Octopus", ItemType.FISH, 500, "fish/octopus.png");
            Item barracuda = new Item("Barracuda", ItemType.FISH, 150, "fish/barracuda.png");
            Item bluefish = new Item("Bluefish", ItemType.FISH, 225, "fish/bluefish.png");
            Item bowfin = new Item("Bowfin", ItemType.FISH, 100, "fish/bowfin.png");
            Item brookTrout = new Item("Brook Trout", ItemType.FISH, 75, "fish/brook-trout.png");
            Item catfish = new Item("Catfish", ItemType.FISH, 200, "fish/catfish.png");
            Item crappie = new Item("Crappie", ItemType.FISH, 175, "fish/crappie.png");
            Item eel = new Item("Eel", ItemType.FISH, 210, "fish/eel.png");
            Item flounder = new Item("Flounder", ItemType.FISH, 425, "fish/flounder.png");
            Item lobster = new Item("Lobster", ItemType.FISH, 625, "fish/lobster.png");
            Item mikfish = new Item("Mikfish", ItemType.FISH, 300, "fish/mikfish.png");
            Item murrayCod = new Item("Murray Cod", ItemType.FISH, 700, "fish/murray-cod.png");
            Item papuaBlackSnapper = new Item("Papua Black Snapper", ItemType.FISH, 550,
                    "fish/papua-black-snapper.png");
            Item rainbowTrout = new Item("Rainbow Trout", ItemType.FISH, 100, "fish/rainbow-trout.png");
            Item ray = new Item("Ray", ItemType.FISH, 800, "fish/ray.png");
            Item salmon = new Item("Salmon", ItemType.FISH, 1000, "fish/salmon.png");
            Item seabass = new Item("Seabass", ItemType.FISH, 675, "fish/seabass.png");
            Item squid = new Item("Squid", ItemType.FISH, 350, "fish/squid.png");
            Item swordfish = new Item("Swordfish", ItemType.FISH, 1500, "fish/swordfish.png");
            Item whitefish = new Item("Whitefish", ItemType.FISH, 310, "fish/whitedish.png");

            repository.saveAll(List.of(octopus, barracuda, bluefish, bowfin, brookTrout, catfish, crappie, eel,
                    flounder, lobster, mikfish, murrayCod, papuaBlackSnapper, rainbowTrout, ray, salmon, seabass, squid,
                    swordfish, whitefish));
        };
    }

}
