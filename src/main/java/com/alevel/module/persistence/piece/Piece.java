package com.alevel.module.persistence.piece;

import com.alevel.module.persistence.chessboard.Chessboard;
import com.alevel.module.persistence.chessboard.Move;
import com.alevel.module.persistence.piece.configs.Color;
import com.alevel.module.persistence.piece.configs.Type;


public abstract class Piece {
    public static int[][] ALLOWED_SPECIFIC_MOVES; // TODO refactor (shouldn't be an empty constant)

    private Color color;
    private Type type;
    private boolean isCaptured; // won't be stored in the db
    // TODO: add additional rules, i.e. if special features are provided (promotion, castling), specific rules apply

    public abstract boolean doMove(Move move, Chessboard chessboard);

    public Piece(Color color, Type type) {
        this.color = color;
        this.type = type;
    }

    // TODO: implement
    // TODO validate: check moves validity
    /*
    public Vector getVector(Move move) {

    }
    */

    public boolean isCaptured() {
        return isCaptured;
    }

    public void setCaptured(boolean captured) {
        isCaptured = captured;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
