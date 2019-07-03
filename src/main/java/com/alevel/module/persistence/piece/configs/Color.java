package com.alevel.module.persistence.piece.configs;

import com.alevel.module.persistence.chessboard.configs.File;
import com.fasterxml.jackson.annotation.JsonCreator;

public enum Color {
    BLACK("black"),
    WHITE("white");

    private String shortTitle;

    Color (String shortTitle) {
        this.shortTitle = shortTitle;
    }

    @Override
    public String toString() {
        return shortTitle;
    }

    @JsonCreator
    public static Color create(String value) {
        if(value == null) {
            throw new IllegalArgumentException("Please provide a Color value.");
        }
        for(Color v : values()) {
            if(value.equals(v.getShortTitle())) {
                return v;
            }
        }
        throw new IllegalArgumentException("Please provide a correct Color value.");
    }

    public String getShortTitle() {
        return shortTitle;
    }
}
