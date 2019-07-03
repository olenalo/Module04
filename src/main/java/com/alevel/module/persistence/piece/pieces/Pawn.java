package com.alevel.module.persistence.piece.pieces;

import com.alevel.module.persistence.chessboard.Chessboard;
import com.alevel.module.persistence.chessboard.Move;
import com.alevel.module.persistence.piece.Piece;
import com.alevel.module.persistence.piece.configs.Color;

import static com.alevel.module.persistence.piece.configs.MovementRules.PAWN_ALLOWED_MOVEMENT_DELTAS;
import static com.alevel.module.persistence.piece.configs.Type.PAWN;

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
        // TODO
        return false;
    }

}
