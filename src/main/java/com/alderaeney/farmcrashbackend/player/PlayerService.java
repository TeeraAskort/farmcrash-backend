package com.alderaeney.farmcrashbackend.player;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {

    private final PlayerRepository repository;

    @Autowired
    public PlayerService(PlayerRepository repository) {
        this.repository = repository;
    }

    public Optional<Player> getPlayerById(Long id) {
        return repository.findById(id);
    }

    public void removePlayerById(Long id) {
        repository.deleteById(id);
    }

    public List<Player> getAllPlayers() {
        return repository.findAll();
    }

    public List<Player> getLeaderBoard() {
        return repository.findTop5ByOrderByMoneyDesc();
    }

    public Player addPlayer(Player player) {
        return repository.saveAndFlush(player);
    }

}