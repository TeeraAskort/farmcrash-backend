package com.alderaeney.farmcrashbackend.player;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {

    List<Player> findTop5ByOrderByMoneyDesc();

    Optional<Player> findTopByOrderByIdDesc();

    Optional<Player> findByName(String name);
}
