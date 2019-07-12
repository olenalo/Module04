package com.alevel.module.service.repository;

import com.alevel.module.model.game.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlayerRepository  extends JpaRepository<Player, Long> {

    Optional<Player> findByUsername(String username);

}
