package com.alevel.module.persistence.pieces.pieces;

import com.alevel.module.persistence.chessboard.Chessboard;
import com.alevel.module.persistence.chessboard.Move;
import com.alevel.module.persistence.pieces.Piece;
import com.alevel.module.persistence.pieces.configs.Color;

import static com.alevel.module.persistence.pieces.configs.Type.ROOK;

public class Rook extends Piece {  // could be `Castle`
    // TODO implement

    public Rook(Color color) {
        super(color, ROOK);
    }

    @Override
    public boolean doMove(Move move, Chessboard chessboard) {
        // TODO
        return false;
    }
}
