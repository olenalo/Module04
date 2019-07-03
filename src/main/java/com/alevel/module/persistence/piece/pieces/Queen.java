package com.alevel.module.persistence.piece.pieces;

import com.alevel.module.persistence.chessboard.Chessboard;
import com.alevel.module.persistence.chessboard.Move;
import com.alevel.module.persistence.piece.Piece;
import com.alevel.module.persistence.piece.configs.Color;

import static com.alevel.module.persistence.piece.configs.Type.QUEEN;


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
