package com.alevel.module.model.chessboard;

import java.util.Collections;
import java.util.List;

/**
 * Record history of moves.
 */
public class Vector {
    private List<Space> spaces;

    public Vector(List<Space> spaces) {
        this.spaces = Collections.unmodifiableList(spaces);
    }
}
