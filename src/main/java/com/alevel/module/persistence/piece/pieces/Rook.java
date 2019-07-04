package com.alevel.module.persistence.piece.pieces;

import com.alevel.module.persistence.chessboard.Chessboard;
import com.alevel.module.persistence.chessboard.Move;
import com.alevel.module.persistence.piece.Piece;
import com.alevel.module.persistence.piece.configs.Color;

import static com.alevel.module.persistence.piece.configs.MovementRules.ROOK_ALLOWED_MOVEMENT_DELTAS;
import static com.alevel.module.persistence.piece.configs.Type.ROOK;

public class Rook extends Piece {  // could be `Castle`

    public Rook(Color color) {
        super(color, ROOK);
        // this.allowedMovementDeltas = ROOK_ALLOWED_MOVEMENT_DELTAS;
    }

    // Ref.: https://stackoverflow.com/a/51014378
    public Rook() {}

    @Override
    public boolean doMove(Move move, Chessboard chessboard) {
        return super.validatePerMovementRules(move, ROOK_ALLOWED_MOVEMENT_DELTAS);
        // TODO do the rest
    }

    @Override
    public String toString() {
        return "Rook{" +
                "color=" + this.getColor() +
                ", type=" + this.getType() +
                '}';
    }
}
