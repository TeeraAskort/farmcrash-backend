package com.alderaeney.farmcrashbackend.player;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PlayerService implements UserDetailsService {

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

    public Optional<Player> findPlayerByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Player> player = repository.findByName(username);
        if (player.isPresent()) {
            return player.get();
        }
        return null;
    }

}