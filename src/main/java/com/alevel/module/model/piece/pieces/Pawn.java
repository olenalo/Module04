package com.alevel.module.model.piece.pieces;

import com.alevel.module.controller.exceptions.InvalidMoveException;
import com.alevel.module.model.chessboard.Chessboard;
import com.alevel.module.model.chessboard.Move;
import com.alevel.module.model.piece.Piece;
import com.alevel.module.model.piece.configs.Color;

import static com.alevel.module.model.piece.configs.rules.MovementRules.PAWN_ALLOWED_MOVEMENT_DELTAS;
import static com.alevel.module.model.piece.configs.Type.PAWN;

public class Pawn extends Piece {

    public Pawn(Color color) {
        super(color, PAWN);
        this.allowedMovementDeltas = PAWN_ALLOWED_MOVEMENT_DELTAS;
    }

    public Pawn() {
        this.type = PAWN;
        this.allowedMovementDeltas = PAWN_ALLOWED_MOVEMENT_DELTAS;
    }

    @Override
    public String toString() {
        return "Pawn{" +
                "color=" + this.getColor() +
                ", type=" + this.getType() +
                '}';
    }
}
