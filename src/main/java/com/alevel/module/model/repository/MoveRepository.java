package com.alevel.module.model.repository;

import com.alevel.module.model.chessboard.Move;
import com.alevel.module.model.game.Game;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MoveRepository extends JpaRepository<Move, Long> {
    List<Move> findByGame(Game game);
}
