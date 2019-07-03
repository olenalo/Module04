package com.alevel.module.persistence.piece.pieces;

import com.alevel.module.persistence.chessboard.Chessboard;
import com.alevel.module.persistence.chessboard.Move;
import com.alevel.module.persistence.piece.Piece;
import com.alevel.module.persistence.piece.configs.Color;

import static com.alevel.module.persistence.piece.configs.MovementRules.KING_ALLOWED_MOVEMENT_DELTAS;
import static com.alevel.module.persistence.piece.configs.Type.KING;

public class King extends Piece {

    public King(Color color) {
        super(color, KING);
        // this.allowedMovementDeltas = KING_ALLOWED_MOVEMENT_DELTAS;
    }

    // Ref.: https://stackoverflow.com/a/51014378
    public King() {}

    @Override
    public boolean doMove(Move move, Chessboard chessboard) {
        return super.validatePerMovementRules(move, KING_ALLOWED_MOVEMENT_DELTAS);
        // TODO check compliance with specific rules
        // TODO add validateCheck () (King always needs it) -
        //  ensure the move doesn't put a king in check

        // TODO implement the move (if valid)

        // TODO
        //  validateCheckMate();
    }


}
