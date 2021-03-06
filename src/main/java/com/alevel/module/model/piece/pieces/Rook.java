package com.alevel.module.model.piece.pieces;

import com.alevel.module.controller.exceptions.InvalidMoveException;
import com.alevel.module.model.chessboard.Chessboard;
import com.alevel.module.model.chessboard.Move;
import com.alevel.module.model.piece.Piece;
import com.alevel.module.model.piece.configs.Color;

import static com.alevel.module.model.piece.configs.rules.MovementRules.ROOK_ALLOWED_MOVEMENT_DELTAS;
import static com.alevel.module.model.piece.configs.Type.ROOK;

public class Rook extends Piece {

    public Rook(Color color) {
        super(color, ROOK);
        this.allowedMovementDeltas = ROOK_ALLOWED_MOVEMENT_DELTAS;
    }

    public Rook() {
        this.type = ROOK;
        this.allowedMovementDeltas = ROOK_ALLOWED_MOVEMENT_DELTAS;
    }

    @Override
    public String toString() {
        return "Rook{" +
                "color=" + this.getColor() +
                ", type=" + this.getType() +
                '}';
    }
}
