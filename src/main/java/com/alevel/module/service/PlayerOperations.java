package com.alevel.module.service;

import com.alevel.module.model.game.Player;

import java.util.List;
import java.util.Optional;

public interface PlayerOperations {
    List<Player> findAll();

    Optional<Player> find(Long id);

    void update(Long id, Player player);

    Long save(Player player);

    void delete(Long id);
}
