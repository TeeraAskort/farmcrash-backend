package com.alderaeney.farmcrashbackend.player;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    public Iterable<Player> getAllPlayers() {
        return repository.findAll();
    }

    public List<Player> getLeaderBoard() {
        return repository.findTop5ByOrderByMoneyDesc();
    }

    public Player addPlayer(Player player) {
        return repository.save(player);
    }

    public Optional<Player> findPlayerByName(String name) {
        return repository.findByName(name);
    }

    public Page<Player> searchUsersByName(String name, Integer page) {
        Pageable pageable = PageRequest.of(page, 10);
        return repository.findByNameIgnoreCaseContaining(name, pageable);
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