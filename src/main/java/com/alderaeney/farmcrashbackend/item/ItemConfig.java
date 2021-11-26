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
            Item octopus = new Item("Octopus", ItemType.FISH, 500, "octopus");
            Item barracuda = new Item("Barracuda", ItemType.FISH, 150, "barracuda");
            Item bluefish = new Item("Bluefish", ItemType.FISH, 225, "bluefish");
            Item bowfin = new Item("Bowfin", ItemType.FISH, 100, "bowfin");
            Item brookTrout = new Item("Brook Trout", ItemType.FISH, 75, "brook-trout");
            Item catfish = new Item("Catfish", ItemType.FISH, 200, "catfish");
            Item crappie = new Item("Crappie", ItemType.FISH, 175, "crappie");
            Item eel = new Item("Eel", ItemType.FISH, 210, "eel");
            Item flounder = new Item("Flounder", ItemType.FISH, 425, "flounder");
            Item lobster = new Item("Lobster", ItemType.FISH, 625, "lobster");
            Item mikfish = new Item("Mikfish", ItemType.FISH, 300, "mikfish");
            Item murrayCod = new Item("Murray Cod", ItemType.FISH, 700, "murray-cod");
            Item papuaBlackSnapper = new Item("Papua Black Snapper", ItemType.FISH, 550, "papua-black-snapper");
            Item rainbowTrout = new Item("Rainbow Trout", ItemType.FISH, 100, "rainbow-trout");
            Item ray = new Item("Ray", ItemType.FISH, 800, "ray");
            Item salmon = new Item("Salmon", ItemType.FISH, 1000, "salmon");
            Item seabass = new Item("Seabass", ItemType.FISH, 675, "seabass");
            Item squid = new Item("Squid", ItemType.FISH, 350, "squid");
            Item swordfish = new Item("Swordfish", ItemType.FISH, 1500, "swordfish");
            Item whitefish = new Item("Whitefish", ItemType.FISH, 310, "whitedish");

            repository.saveAll(List.of(octopus, barracuda, bluefish, bowfin, brookTrout, catfish, crappie, eel,
                    flounder, lobster, mikfish, murrayCod, papuaBlackSnapper, rainbowTrout, ray, salmon, seabass, squid,
                    swordfish, whitefish));
        };
    }

}
