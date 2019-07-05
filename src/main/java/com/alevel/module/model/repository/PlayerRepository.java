package com.alevel.module.model.repository;

import com.alevel.module.model.game.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository  extends JpaRepository<Player, Long> {
}
