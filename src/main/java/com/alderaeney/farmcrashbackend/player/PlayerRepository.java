package com.alderaeney.farmcrashbackend.player;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends PagingAndSortingRepository<Player, Long> {

    List<Player> findTop5ByOrderByMoneyDesc();

    Optional<Player> findTopByOrderByIdDesc();

    Optional<Player> findByName(String name);

    List<Player> findByNameIgnoreCaseContaining(String name, Pageable pageable);
}
