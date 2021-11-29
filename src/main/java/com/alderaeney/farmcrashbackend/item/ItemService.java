package com.alderaeney.farmcrashbackend.item;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

    private final ItemRepository repository;

    @Autowired
    public ItemService(ItemRepository repository) {
        this.repository = repository;
    }

    public List<Item> getAllItems() {
        return repository.findAll();
    }

    public Item findRandomItemByType(ItemType type) {
        return repository.findRandomByType(type).get(0);
    }

    public void saveItem(Item item) {
        repository.save(item);
    }

    public void removeItem(Long id) {
        repository.deleteById(id);
    }

}
