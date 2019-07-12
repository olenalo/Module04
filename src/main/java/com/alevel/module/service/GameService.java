package com.alevel.module.service;

import com.alevel.module.model.game.Game;
import com.alevel.module.service.operation.GameOperations;
import com.alevel.module.service.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;
import java.util.Optional;

@Service
public class GameService implements GameOperations {

    private GameRepository gameRepository;

    @Autowired
    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public List<Game> findAll() {
        return gameRepository.findAll();
    }

    @Override
    public Optional<Game> find(Long id) {
        return gameRepository.findById(id);
    }

    @Override
    public void update(Long id, Game game) {
        throw new NotImplementedException();
    }

    @Override
    public Long save(Game game) {
        return gameRepository.save(game).getId();
    }

    @Override
    public void delete(Long id) {
        gameRepository.deleteById(id);
    }
}
