package com.alevel.module.persistence.chessboard;

import com.alevel.module.persistence.pieces.Piece;

/**
 * TODO add docstring
 *
 * Only valid move will be saved to the db.
 */
public class Move {

    private int timestamp;
    // Note: color information is encapsulated in a piece (color indicates a player)
    // If King and Rook are being castled, two pieces are involved TODO but maybe it should be two separate moves;
    //  also, TODO what about cells? figure out...
    private Piece piece;
    private Space currentSpace; // before
    private Space destinationSpace; // after
    // TODO results (captured pieces if any)

    // TODO add constructor

    public boolean validate() {
        // TODO
        //  0 - ensure the move is within the field -- this belongs to the `Square` class
        //  1 - general rules
        //  2 - if does not comply with general rules, check specific rules if eligible
        //    e.g. for King / Rook - check if ever moved (if castling is attempted);
        //    think about Pawn
        //  3 - check if cell is not occupied by pieces of same color   -- this belongs to the `Square` class
        //  4 - validate if checkmate
        //  other checks?

        // TODO IllegalArgumentException
        return true;
    }

    /**
     * Check if checkmate is in effect.
     *
     * i.e. check if the game is won.
     *
     * @return boolean: true if checkmate is reached, false if not.
     */
    public boolean validateCheckMate() {
        // TODO
        return true;
    }

    // TODO add validateCheck () (King always needs it)

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
}
