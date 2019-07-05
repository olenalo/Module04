package com.alevel.module.model.repository;

import com.alevel.module.model.chessboard.Move;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MoveRepository extends JpaRepository<Move, Long> {
}
