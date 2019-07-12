package com.alevel.module.service.repository;

import com.alevel.module.model.chessboard.Move;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MoveRepository extends JpaRepository<Move, Long> {
    List<Move> findByGameId(Long gameId);
}
