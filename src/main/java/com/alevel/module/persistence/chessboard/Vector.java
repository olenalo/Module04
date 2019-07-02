package com.alevel.module.persistence.chessboard;

import java.util.Collections;
import java.util.List;

public class Vector {
    private List<Space> spaces;

    public Vector(List<Space> spaces) {
        this.spaces = Collections.unmodifiableList(spaces);
    }
}
