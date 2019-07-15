package com.alevel.module.model.piece;

import com.alevel.module.model.chessboard.Space;

import java.util.Collections;
import java.util.List;

/**
 * Vector (path) of piece moves' spaces.
 */
public class Vector {

    private List<Space> spaces;

    public Vector(List<Space> spaces) {
        this.spaces = Collections.unmodifiableList(spaces);
    }

    public Vector() {
    }

    public List<Space> getSpaces() {
        return spaces;
    }

    public void setSpaces(List<Space> spaces) {
        this.spaces = spaces;
    }
}
