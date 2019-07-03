package com.alevel.module.persistence.chessboard;

import com.alevel.module.persistence.piece.Piece;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * TODO add docstring
 *
 * Only valid move will be saved to the db
 */
public class Move {

    private int timestamp;  // TODO make final, assigning the current timestamp
    // If King and Rook are being castled, two piece are involved TODO but maybe it should be two separate moves;
    //  also, TODO what about cells? figure out...
    private Piece piece;
    private Space currentSpace;
    private Space destinationSpace;
    // TODO results (captured piece if any)

    public Move(Piece piece, Space currentSpace, Space destinationSpace) {
        this.piece = piece;
        this.currentSpace = currentSpace;
        this.destinationSpace = destinationSpace;
    }

    // Ref.: https://stackoverflow.com/a/51014378
    public Move() {
    }

    public boolean validate() {
        // TODO
        //  0 - Square: ensure the move is within the field -- this belongs to the `Square` class
        //  1 - Piece: general rules
        //  2 - Piece: if does not comply with general rules, check specific rules if eligible
        //    e.g. for King / Rook - check if ever moved (if castling is attempted);
        //    think about Pawn
        //  3 - Square: check if cell is not occupied by piece of same color
        //  4 - Piece: validate if checkmate
        //  other checks?

        // TODO IllegalArgumentException
        return true;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public Space getDestinationSpace() {
        return destinationSpace;
    }

    public void setDestinationSpace(Space destinationSpace) {
        this.destinationSpace = destinationSpace;
    }

    public Space getCurrentSpace() {
        return currentSpace;
    }

    public void setCurrentSpace(Space currentSpace) {
        this.currentSpace = currentSpace;
    }

    public Piece getPiece() {
        return piece;
    }

    @Override
    public String toString() {
        return "Move{" +
                "piece=" + piece +
                ", currentSpace=" + currentSpace +
                ", destinationSpace=" + destinationSpace +
                '}';
    }
}
