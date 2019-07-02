package com.alevel.module.persistence.pieces.pieces;

import com.alevel.module.persistence.chessboard.Chessboard;
import com.alevel.module.persistence.chessboard.Move;
import com.alevel.module.persistence.pieces.Piece;
import com.alevel.module.persistence.pieces.configs.Color;
import com.alevel.module.persistence.pieces.configs.Type;

import static com.alevel.module.persistence.pieces.configs.Type.KING;

public class King extends Piece {
    public static final Type TITLE = KING;
    public static final int[][] ALLOWED_GENERAL_MOVES = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    // TODO add castling if never moved (for Rook as well), ref.: https://en.wikipedia.org/wiki/Castling
    //  public static int[][] ALLOWED_SPECIFIC_MOVES = {};

    public King(Color color) {
        super(color, KING);
    }

    @Override
    public boolean doMove(Move move, Chessboard chessboard) {
        // TODO
        return false;
    }


}
