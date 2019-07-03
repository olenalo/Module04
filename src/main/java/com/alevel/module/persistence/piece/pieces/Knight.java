package com.alevel.module.persistence.piece.pieces;

import com.alevel.module.persistence.chessboard.Chessboard;
import com.alevel.module.persistence.chessboard.Move;
import com.alevel.module.persistence.piece.Piece;
import com.alevel.module.persistence.piece.configs.Color;

import static com.alevel.module.persistence.piece.configs.MovementRules.KNIGHT_ALLOWED_MOVEMENT_DELTAS;
import static com.alevel.module.persistence.piece.configs.Type.KNIGHT;

public class Knight extends Piece {

    public Knight(Color color) {
        super(color, KNIGHT);
        // this.allowedMovementDeltas = KNIGHT_ALLOWED_MOVEMENT_DELTAS;
    }

    // Ref.: https://stackoverflow.com/a/51014378
    public Knight() {}

    @Override
    public boolean doMove(Move move, Chessboard chessboard) {
        return super.validatePerMovementRules(move, KNIGHT_ALLOWED_MOVEMENT_DELTAS);
        // TODO do the rest
    }

}