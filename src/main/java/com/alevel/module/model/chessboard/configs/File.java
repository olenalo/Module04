package com.alevel.module.model.chessboard.configs;

import com.fasterxml.jackson.annotation.JsonCreator;

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

    @JsonCreator
    public static File create(String value) {
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

