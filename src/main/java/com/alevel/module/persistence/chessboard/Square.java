package com.alevel.module.persistence.chessboard;

import com.alevel.module.persistence.piece.Piece;

public class Square {

    private Space space;
    private Piece piece;

    public Square(Space space, Piece piece) {
        // TODO validate: ensure the move is within the field (here and in another constructor)
        // TODO validate: check if space is not occupied by piece of same color (here and in another constructor)
        this.space = space;
        this.piece = piece;
    }

    public Square(Space space) {
        // TODO validate
        this.space = space;
    }

    public Space getSpace() {
        return space;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }
}
