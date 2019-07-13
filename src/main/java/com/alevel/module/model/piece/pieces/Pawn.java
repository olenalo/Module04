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

    public boolean validateMove(Move move, Chessboard chessboard) throws InvalidMoveException {
        // TODO Pawn chess pieces can only directly forward one square, with two exceptions. (--> check per color to ensure forward movement)
        // TODO Pawns can move directly forward two squares on their first move only. (--> check if first move)
        // TODO Pawns can move diagonally forward when capturing an opponent's chess piece (--> check if capture)
        return super.validateMove(move, chessboard);
    }

    @Override
    public String toString() {
        return "Pawn{" +
                "color=" + this.getColor() +
                ", type=" + this.getType() +
                '}';
    }
}
