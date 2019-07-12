package com.alevel.module.service.operation;

import com.alevel.module.model.game.Game;

import java.util.List;
import java.util.Optional;

public interface GameOperations {

    List<Game> findAll();

    Optional<Game> find(Long id);

    void update(Long id, Game game);

    Long save(Game game);

    void delete(Long id);

}
