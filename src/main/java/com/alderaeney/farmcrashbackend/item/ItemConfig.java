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
            Item octopus = new Item("Octopus", ItemType.FISH, 500, "octopus", false);
            Item barracuda = new Item("Barracuda", ItemType.FISH, 150, "barracuda", false);
            Item bluefish = new Item("Bluefish", ItemType.FISH, 225, "bluefish", false);
            Item bowfin = new Item("Bowfin", ItemType.FISH, 100, "bowfin", false);
            Item brookTrout = new Item("Brook Trout", ItemType.FISH, 75, "brook-trout", false);
            Item catfish = new Item("Catfish", ItemType.FISH, 200, "catfish", false);
            Item crappie = new Item("Crappie", ItemType.FISH, 175, "crappie", false);
            Item eel = new Item("Eel", ItemType.FISH, 210, "eel", false);
            Item flounder = new Item("Flounder", ItemType.FISH, 425, "flounder", false);
            Item lobster = new Item("Lobster", ItemType.FISH, 625, "lobster", false);
            Item mikfish = new Item("Mikfish", ItemType.FISH, 300, "milkfish", false);
            Item murrayCod = new Item("Murray Cod", ItemType.FISH, 700, "murray-cod", false);
            Item papuaBlackSnapper = new Item("Papua Black Snapper", ItemType.FISH, 550, "papua-black-snapper", false);
            Item rainbowTrout = new Item("Rainbow Trout", ItemType.FISH, 100, "rainbow-trout", false);
            Item ray = new Item("Ray", ItemType.FISH, 800, "ray", false);
            Item salmon = new Item("Salmon", ItemType.FISH, 1000, "salmon", false);
            Item seabass = new Item("Seabass", ItemType.FISH, 675, "seabass", false);
            Item squid = new Item("Squid", ItemType.FISH, 350, "squid", false);
            Item swordfish = new Item("Swordfish", ItemType.FISH, 1500, "swordfish", false);
            Item whitefish = new Item("Whitefish", ItemType.FISH, 310, "whitedish", false);

            repository.saveAll(List.of(octopus, barracuda, bluefish, bowfin, brookTrout, catfish, crappie, eel,
                    flounder, lobster, mikfish, murrayCod, papuaBlackSnapper, rainbowTrout, ray, salmon, seabass, squid,
                    swordfish, whitefish));
        };
    }

}
