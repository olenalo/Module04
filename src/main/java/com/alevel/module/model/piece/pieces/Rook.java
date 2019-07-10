package com.alevel.module.model.piece.pieces;

import com.alevel.module.model.chessboard.Chessboard;
import com.alevel.module.model.chessboard.Move;
import com.alevel.module.model.piece.Piece;
import com.alevel.module.model.piece.configs.Color;

import static com.alevel.module.model.piece.configs.rules.MovementRules.ROOK_ALLOWED_MOVEMENT_DELTAS;
import static com.alevel.module.model.piece.configs.Type.ROOK;

public class Rook extends Piece {  // could be `Castle`

    public Rook(Color color) {
        super(color, ROOK);
        // this.allowedMovementDeltas = ROOK_ALLOWED_MOVEMENT_DELTAS;
    }

    // Ref.: https://stackoverflow.com/a/51014378
    public Rook() {}

    @Override
    public boolean doMove(Move move, Chessboard chessboard) {
        // TODO check compliance with specific rules
        if (super.validatePerMovementRules(move, ROOK_ALLOWED_MOVEMENT_DELTAS)) {
            return validateCheckMate();
        } else {
            return false;
        }

    }

    @Override
    public String toString() {
        return "Rook{" +
                "color=" + this.getColor() +
                ", type=" + this.getType() +
                '}';
    }
}
