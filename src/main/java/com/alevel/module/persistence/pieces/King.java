package com.alevel.module.persistence.pieces;

import com.alevel.module.persistence.configs.Color;
import com.alevel.module.persistence.configs.PieceType;

import java.util.ArrayList;

import static com.alevel.module.persistence.configs.PieceType.KING;

public class King extends Piece {
    public static final PieceType TITLE = KING;
    public static final int[][] ALLOWED_GENERAL_MOVES = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    // TODO add castling if never moved (for Rook as well), ref.: https://en.wikipedia.org/wiki/Castling
    //  public static int[][] ALLOWED_SPECIFIC_MOVES = {};

}
