package com.alevel.module.model.piece.pieces;

import com.alevel.module.model.chessboard.Chessboard;
import com.alevel.module.model.chessboard.Move;
import com.alevel.module.model.piece.Piece;
import com.alevel.module.model.piece.configs.Color;

import static com.alevel.module.model.piece.configs.rules.MovementRules.BISHOP_ALLOWED_MOVEMENT_DELTAS;
import static com.alevel.module.model.piece.configs.Type.BISHOP;

public class Bishop extends Piece {

    public Bishop(Color color) {
        super(color, BISHOP);
        // this.allowedMovementDeltas = BISHOP_ALLOWED_MOVEMENT_DELTAS;
    }

    // Ref.: https://stackoverflow.com/a/51014378
    public Bishop() {}

    @Override
    public boolean validateMove(Move move, Chessboard chessboard) {
        return super.validatePerMovementRules(move, BISHOP_ALLOWED_MOVEMENT_DELTAS);
    }

    @Override
    public String toString() {
        return "Bishop{" +
                "color=" + this.getColor() +
                ", type=" + this.getType() +
                '}';
    }
}
