package com.alevel.module.persistence.pieces.pieces;

import com.alevel.module.persistence.chessboard.Chessboard;
import com.alevel.module.persistence.chessboard.Move;
import com.alevel.module.persistence.pieces.Piece;
import com.alevel.module.persistence.pieces.configs.Color;

import static com.alevel.module.persistence.pieces.configs.Type.BISHOP;

public class Bishop extends Piece {

    // TODO implement
    public Bishop(Color color) {
        super(color, BISHOP);
    }

    @Override
    public boolean doMove(Move move, Chessboard chessboard) {
        // TODO
        return false;
    }

}
