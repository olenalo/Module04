package com.alevel.module.persistence.piece.pieces;

import com.alevel.module.persistence.chessboard.Chessboard;
import com.alevel.module.persistence.chessboard.Move;
import com.alevel.module.persistence.piece.Piece;
import com.alevel.module.persistence.piece.configs.Color;

import static com.alevel.module.persistence.piece.configs.Type.ROOK;

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
