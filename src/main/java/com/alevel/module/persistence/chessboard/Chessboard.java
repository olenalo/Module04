package com.alevel.module.persistence.chessboard;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.oracle.webservices.internal.api.databinding.DatabindingMode;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * Build up state from squares.
 *
 * Occupied squares go first.
 *
 * TODO Iteration should stop once all occupied squares are iterated over
 *  i.e. once an empty square appears.
 */
public class Chessboard {

    private final UUID id  = UUID.randomUUID();

    private List<Square> squares;

    public Chessboard(List<Square> squares) {
        this.squares = Collections.unmodifiableList(squares);
    }

    public boolean isSpaceEmpty(Space space) {
        // TODO squares.forEach()
        return true;
    }

    public List<Square> getSquares() {
        return squares;
    }

    public void setSquares(List<Square> squares) {
        this.squares = squares;
    }
}
