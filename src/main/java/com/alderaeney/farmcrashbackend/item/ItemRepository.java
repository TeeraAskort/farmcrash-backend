package com.alderaeney.farmcrashbackend.item;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findAllItemsByType(ItemType type);

    @Query(value = "SELECT i FROM Item i WHERE i.type = ?1 ORDER BY FUNCTION('RAND')")
    List<Item> findRandomByType(ItemType type);
}
