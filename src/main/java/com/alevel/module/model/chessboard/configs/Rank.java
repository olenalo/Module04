package com.alevel.module.model.chessboard.configs;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 *  Space rank types.
 */
public enum Rank {
    ONE("1"),
    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5"),
    SIX("6"),
    SEVEN("7"),
    EIGHT("8");

    // TODO DRY (see `File` for duplicate code)
    private String shortTitle;

    Rank (String shortTitle) {
        this.shortTitle = shortTitle;
    }

    @Override
    public String toString() {
        return shortTitle;
    }

    /**
     *  Provide for (de)serialization.
     *
     *  It also allows for validating if
     *  chess moves are within the field.
     *
     * @param value
     * @return
     * @throws IllegalArgumentException
     */
    @JsonCreator
    public static Rank create(String value) throws IllegalArgumentException {
        if(value == null) {
            throw new IllegalArgumentException("Please provide a Rank value.");
        }
        for(Rank v : values()) {
            if(value.equals(v.getShortTitle())) {
                return v;
            }
        }
        throw new IllegalArgumentException("Please provide a correct Rank value.");
    }

    public String getShortTitle() {
        return shortTitle;
    }
}
