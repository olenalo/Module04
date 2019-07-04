package com.alevel.module.persistence.piece.pieces;

import com.alevel.module.persistence.chessboard.Chessboard;
import com.alevel.module.persistence.chessboard.Move;
import com.alevel.module.persistence.piece.Piece;
import com.alevel.module.persistence.piece.configs.Color;

import static com.alevel.module.persistence.piece.configs.MovementRules.BISHOP_ALLOWED_MOVEMENT_DELTAS;
import static com.alevel.module.persistence.piece.configs.Type.BISHOP;

public class Bishop extends Piece {

    public Bishop(Color color) {
        super(color, BISHOP);
        // this.allowedMovementDeltas = BISHOP_ALLOWED_MOVEMENT_DELTAS;
    }

    // Ref.: https://stackoverflow.com/a/51014378
    public Bishop() {}

    @Override
    public boolean doMove(Move move, Chessboard chessboard) {
        return super.validatePerMovementRules(move, BISHOP_ALLOWED_MOVEMENT_DELTAS);
        // TODO do the rest
    }

    @Override
    public String toString() {
        return "Bishop{" +
                "color=" + this.getColor() +
                ", type=" + this.getType() +
                '}';
    }
}
