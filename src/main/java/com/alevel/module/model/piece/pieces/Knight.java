package com.alevel.module.model.piece.pieces;

import com.alevel.module.model.chessboard.Chessboard;
import com.alevel.module.model.chessboard.Move;
import com.alevel.module.model.piece.Piece;
import com.alevel.module.model.piece.configs.Color;

import static com.alevel.module.model.piece.configs.rules.MovementRules.KNIGHT_ALLOWED_MOVEMENT_DELTAS;
import static com.alevel.module.model.piece.configs.Type.KNIGHT;

public class Knight extends Piece {

    public Knight(Color color) {
        super(color, KNIGHT);
        this.allowedMovementDeltas = KNIGHT_ALLOWED_MOVEMENT_DELTAS;
    }

    public Knight() {
        this.type = KNIGHT;
        this.allowedMovementDeltas = KNIGHT_ALLOWED_MOVEMENT_DELTAS;
    }

    @Override
    public boolean validateMove(Move move, Chessboard chessboard) {
        this.setVector(move);
        return super.validatePerMovementRules(move) && chessboard.validateMove(move);
    }

    @Override
    public String toString() {
        return "Knight{" +
                "color=" + this.getColor() +
                ", type=" + this.getType() +
                '}';
    }
}