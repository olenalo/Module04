package com.alevel.module.model.piece.pieces;

import com.alevel.module.model.chessboard.Chessboard;
import com.alevel.module.model.chessboard.Move;
import com.alevel.module.model.piece.Piece;
import com.alevel.module.model.piece.configs.Color;

import static com.alevel.module.model.piece.configs.MovementRules.QUEEN_ALLOWED_MOVEMENT_DELTAS;
import static com.alevel.module.model.piece.configs.Type.QUEEN;


public class Queen extends Piece {

    public Queen(Color color) {
        super(color, QUEEN);
        // this.allowedMovementDeltas = QUEEN_ALLOWED_MOVEMENT_DELTAS;
    }

    // Ref.: https://stackoverflow.com/a/51014378
    public Queen() {}

    @Override
    public boolean doMove(Move move, Chessboard chessboard) {
        return super.validatePerMovementRules(move, QUEEN_ALLOWED_MOVEMENT_DELTAS);
        // TODO do the rest
    }

    @Override
    public String toString() {
        return "Queen{" +
                "color=" + this.getColor() +
                ", type=" + this.getType() +
                '}';
    }
}