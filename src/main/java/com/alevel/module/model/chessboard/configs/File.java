package com.alevel.module.model.chessboard.configs;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 *  Space file types.
 */
public enum File {
    A("a"),
    B("b"),
    C("c"),
    D("d"),
    E("e"),
    F("f"),
    G("g"),
    H("h");

    private String shortTitle;

    File (String shortTitle) {
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
    public static File create(String value) throws IllegalArgumentException {
        if(value == null) {
            throw new IllegalArgumentException("Please provide a File value.");
        }
        for(File v : values()) {
            if(value.equals(v.getShortTitle())) {
                return v;
            }
        }
        throw new IllegalArgumentException("Please provide a correct File value.");
    }

    public String getShortTitle() {
        return shortTitle;
    }
}

