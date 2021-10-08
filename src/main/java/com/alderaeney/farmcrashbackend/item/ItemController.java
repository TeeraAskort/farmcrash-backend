package com.alderaeney.farmcrashbackend.item;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/item")
public class ItemController {

    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping(path = "all")
    public List<Item> getAllItems() {
        return itemService.getAllItems();
    }

    @GetMapping(path = "random/{itemType}")
    public Item getRandomItemByType(@PathVariable("itemType") ItemType type) {
        return itemService.findRandomItemByType(type);
    }

}
