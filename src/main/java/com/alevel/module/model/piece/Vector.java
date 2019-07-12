package com.alevel.module.model.piece;

import com.alevel.module.model.chessboard.Space;

import java.util.Collections;
import java.util.List;

/**
 * Piece moves vector.
 */
public class Vector {

    private List<Space> spaces;

    public Vector(List<Space> spaces) {
        this.spaces = Collections.unmodifiableList(spaces);
    }

    public List<Space> getSpaces() {
        return spaces;
    }

    public void setSpaces(List<Space> spaces) {
        this.spaces = spaces;
    }
}
