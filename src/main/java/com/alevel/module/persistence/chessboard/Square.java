package com.alevel.module.persistence.chessboard;

import com.alevel.module.persistence.pieces.Piece;

public class Square {

    private Space space;
    private Piece piece;

    public Square(Space space, Piece piece) {
        // TODO validate: ensure the move is within the field
        // TODO validate: check if space is not occupied by pieces of same color
        this.space = space;
        this.piece = piece;
    }

    public Space getSpace() {
        return space;
    }

    public Piece getPiece() {
        return piece;
    }
}
