package com.alevel.module.model.piece.pieces;

import com.alevel.module.model.chessboard.Chessboard;
import com.alevel.module.model.chessboard.Move;
import com.alevel.module.model.piece.Piece;
import com.alevel.module.model.piece.configs.Color;

import static com.alevel.module.model.piece.configs.rules.MovementRules.PAWN_ALLOWED_MOVEMENT_DELTAS;
import static com.alevel.module.model.piece.configs.Type.PAWN;

public class Pawn extends Piece {
    // TODO implement promotion (when 8th rank is reached), ref.: https://en.wikipedia.org/wiki/Promotion_(chess)
    // TODO note: in the 1st move, can move to 2 squares (either rank or file)
    // TODO note: captures diagonally

    public Pawn(Color color) {
        super(color, PAWN);
        // this.allowedMovementDeltas = PAWN_ALLOWED_MOVEMENT_DELTAS;
    }

    // Ref.: https://stackoverflow.com/a/51014378
    public Pawn() {}

    @Override
    public boolean doMove(Move move, Chessboard chessboard) {
        return super.validatePerMovementRules(move, PAWN_ALLOWED_MOVEMENT_DELTAS);
        // TODO do the rest
    }

    @Override
    public String toString() {
        return "Pawn{" +
                "color=" + this.getColor() +
                ", type=" + this.getType() +
                '}';
    }
}
