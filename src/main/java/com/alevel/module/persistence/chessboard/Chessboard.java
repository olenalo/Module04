package com.alevel.module.persistence.chessboard;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class Chessboard {

    final UUID id  = UUID.randomUUID();

    private List<Square> squares;

    public Chessboard(List<Square> squares) {
        this.squares = Collections.unmodifiableList(squares);
    }

    public boolean hasPiece(Space space) {
        // TODO squares.forEach()
        return true;
    }
}
