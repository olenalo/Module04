package com.alevel.module.service;

import com.alevel.module.controller.exceptions.PlayerNotFoundException;
import com.alevel.module.model.game.Player;
import com.alevel.module.service.operation.PlayerOperations;
import com.alevel.module.service.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

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
        Optional<Player> playerOptional = playerRepository.findById(id);
        if(!playerOptional.isPresent()) {
            throw new PlayerNotFoundException(id);
        } else {
            return playerOptional;
        }
    }

    @Override
    public Optional<Player> find(String username) {
        return playerRepository.findByUsername(username);
    }

    @Override
    public void update(Long id, Player player) {
        throw new NotImplementedException();
    }

    @Override
    public Long save(Player player) {
        // player.setRoles(Arrays.asList(ROLE_USER));
        return playerRepository.save(player).getId();
    }

    @Override
    public void delete(Long id) {
        playerRepository.deleteById(id);
    }
}
