package com.alevel.module.model.piece.configs;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Type {
    KING("king"),
    QUEEN("queen"),
    ROOK("rook"),
    KNIGHT("knight"),
    BISHOP("bishop"),
    PAWN("pawn");

    private String shortTitle;

    Type (String shortTitle) {
        this.shortTitle = shortTitle;
    }

    @Override
    public String toString() {
        return shortTitle;
    }

    @JsonCreator
    public static Type create(String value) throws IllegalArgumentException {
        if(value == null) {
            throw new IllegalArgumentException("Please provide a Type value.");
        }
        for(Type v : values()) {
            if(value.equals(v.getShortTitle())) {
                return v;
            }
        }
        throw new IllegalArgumentException("Please provide a correct Type value.");
    }

    public String getShortTitle() {
        return shortTitle;
    }
}
