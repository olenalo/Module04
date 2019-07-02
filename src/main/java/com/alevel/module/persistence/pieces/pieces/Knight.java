package com.alevel.module.persistence.pieces.pieces;

import com.alevel.module.persistence.chessboard.Chessboard;
import com.alevel.module.persistence.chessboard.Move;
import com.alevel.module.persistence.pieces.Piece;
import com.alevel.module.persistence.pieces.configs.Color;
import com.alevel.module.persistence.pieces.configs.Type;

import static com.alevel.module.persistence.pieces.configs.Type.KNIGHT;

public class Knight extends Piece {
    // TODO implement

    public Knight(Color color) {
        super(color, KNIGHT);
    }

    @Override
    public boolean doMove(Move move, Chessboard chessboard) {
        // TODO
        return false;
    }

}