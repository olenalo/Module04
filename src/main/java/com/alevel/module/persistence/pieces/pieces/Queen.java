package com.alevel.module.persistence.pieces.pieces;

import com.alevel.module.persistence.chessboard.Chessboard;
import com.alevel.module.persistence.chessboard.Move;
import com.alevel.module.persistence.pieces.Piece;
import com.alevel.module.persistence.pieces.configs.Color;

import static com.alevel.module.persistence.pieces.configs.Type.QUEEN;


public class Queen extends Piece {
    // TODO implement

    public Queen(Color color) {
        super(color, QUEEN);
    }

    @Override
    public boolean doMove(Move move, Chessboard chessboard) {
        // TODO
        return false;
    }

}
