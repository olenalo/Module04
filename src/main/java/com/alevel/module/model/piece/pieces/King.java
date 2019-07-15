package com.alevel.module.model.piece.pieces;

import com.alevel.module.controller.exceptions.InvalidMoveException;
import com.alevel.module.model.chessboard.Chessboard;
import com.alevel.module.model.chessboard.Move;
import com.alevel.module.model.piece.Piece;
import com.alevel.module.model.piece.configs.Color;

import static com.alevel.module.model.piece.configs.rules.MovementRules.KING_ALLOWED_MOVEMENT_DELTAS;
import static com.alevel.module.model.piece.configs.Type.KING;

public class King extends Piece {

    public King(Color color) {
        super(color, KING);
        this.allowedMovementDeltas = KING_ALLOWED_MOVEMENT_DELTAS;
    }

    public King() {
        this.type = KING;
        this.allowedMovementDeltas = KING_ALLOWED_MOVEMENT_DELTAS;
    }

    @Override
    public boolean validateMove(Move move, Chessboard chessboard) throws InvalidMoveException {
        // TODO add validateCheck(): ensure the move doesn't put a King in check
        return super.validateMove(move, chessboard);
    }

    @Override
    public String toString() {
        return "King{" +
                "color=" + this.getColor() +
                ", type=" + this.getType() +
                '}';
    }
}
