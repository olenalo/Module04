package com.alevel.module.model.repository;

import com.alevel.module.model.game.Player;
import com.alevel.module.service.PlayerOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerService implements PlayerOperations {

    private PlayerRepository playerRepository;

    @Autowired
    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    public List<Player> findAll() {
        return playerRepository.findAll();
    }

    @Override
    public Optional<Player> find(Long id) {
        return playerRepository.findById(id);
    }

    @Override
    public void update(Long id, Player player) {
        // TODO
    }

    @Override
    public Long save(Player player) {
        return playerRepository.save(player).getId();
    }

    @Override
    public void delete(Long id) {
        playerRepository.deleteById(id);
    }
}
