package com.alevel.module.service.repository;

import com.alevel.module.model.game.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository  extends JpaRepository<Game, Long> {
}
