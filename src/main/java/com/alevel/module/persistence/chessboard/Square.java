package com.alevel.module.persistence.chessboard;

import com.alevel.module.persistence.piece.Piece;

import java.util.Objects;

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

    public Square() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Square square = (Square) o;
        return Objects.equals(space, square.space);
    }

    @Override
    public int hashCode() {
        return Objects.hash(space);
    }

    @Override
    public String toString() {
        return "Square{" +
                "space=" + space +
                ", piece=" + piece +
                '}';
    }
}
