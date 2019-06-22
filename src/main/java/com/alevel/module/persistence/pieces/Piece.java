package com.alevel.module.persistence.pieces;

import com.alevel.module.persistence.configs.Color;


public class Piece {
    public static int[][] ALLOWED_SPECIFIC_MOVES; // TODO refactor (shouldn't be an empty constant)

    private Color color;
    private boolean isCaptured; // won't be stored in the db
    // TODO: add additional rules, i.e. if special features are provided (promotion, castling), specific rules apply

    public Piece(Color color) {
        this.color = color;
    }

    public Piece() {
    }

    public boolean isCaptured() {
        return isCaptured;
    }

    public void setCaptured(boolean captured) {
        isCaptured = captured;
    }

    public Color getColor() {
        return color;
    }
}
