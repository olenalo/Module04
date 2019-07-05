package com.alevel.module.service;

import com.alevel.module.model.chessboard.Move;

import java.util.List;
import java.util.Optional;

public interface MoveOperations {
    List<Move> findAll();

    Optional<Move> find(Long id);

    void update(Long id, Move move);

    Long save(Move move);

    void delete(Long id);
}
