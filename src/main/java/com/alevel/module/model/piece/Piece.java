package com.alevel.module.model.piece;

import com.alevel.module.model.chessboard.Chessboard;
import com.alevel.module.model.chessboard.Move;
import com.alevel.module.model.piece.configs.Color;
import com.alevel.module.model.piece.configs.Type;
import com.alevel.module.model.piece.pieces.King;
import com.alevel.module.model.piece.pieces.Bishop;
import com.alevel.module.model.piece.pieces.Knight;
import com.alevel.module.model.piece.pieces.Pawn;
import com.alevel.module.model.piece.pieces.Queen;
import com.alevel.module.model.piece.pieces.Rook;
import com.fasterxml.jackson.annotation.*;

import static com.alevel.module.model.chessboard.configs.FileNumericDecoder.FILE_NUMERIC_DECODER;
import static com.alevel.module.model.chessboard.configs.RankNumericDecoder.RANK_NUMERIC_DECODER;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
    @JsonSubTypes.Type(name = "king", value = King.class),
    @JsonSubTypes.Type(name = "bishop", value = Bishop.class),
    @JsonSubTypes.Type(name = "knight", value = Knight.class),
    @JsonSubTypes.Type(name = "pawn", value = Pawn.class),
    @JsonSubTypes.Type(name = "queen", value = Queen.class),
    @JsonSubTypes.Type(name = "rook", value = Rook.class)
})
public abstract class Piece {
    // TODO consider replacing with @POJOBuilderBean
    @JsonProperty("color")
    private Color color;
    @JsonProperty("pieceType")  // FIXME this is an ugly workaround; need to pass both "type" and "pieceType" with JSON
    private Type type;

    // protected int[][] allowedMovementDeltas; // TODO make it work with jackson (shouldn't pass as method arg below!)

    // TODO: add additional rules,
    //    i.e. if special features are available
    //    (promotion, castling), specific rules apply
    // private int[][] getAllowedSpecificMovesDeltas;

    private boolean isCaptured;

    // Ref.: https://stackoverflow.com/a/51014378
    public Piece() {}

    public abstract boolean doMove(Move move, Chessboard chessboard);

    public Piece(Color color, Type type) {
        this.color = color;
        this.type = type;
    }

    /**
     * Validate if a move is allowed.
     *
     * @param move move to validate.
     * @param allowedMovementDeltas move rules to validate a move against.
     * @return true if a move is valid, otherwise false.
     */
    protected boolean validatePerMovementRules(Move move, int[][] allowedMovementDeltas) {
        int expectedFileDelta = FILE_NUMERIC_DECODER.get(move.getDestinationSpace().getFile()) -
                FILE_NUMERIC_DECODER.get(move.getCurrentSpace().getFile());
        int expectedRankDelta = RANK_NUMERIC_DECODER.get(move.getDestinationSpace().getRank()) -
                RANK_NUMERIC_DECODER.get(move.getCurrentSpace().getRank());
        // TODO separate out to a dedicated method
        for (int[] allowedMovesDelta : allowedMovementDeltas) {
            if (expectedFileDelta == allowedMovesDelta[0] && expectedRankDelta == allowedMovesDelta[1]) {
                return true;
            }
        }
        // TODO add specific rules validation
        return false;
    }

    // TODO: implement
    /*
    public Vector getVector(Move move) {

    }
    */

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

    public boolean isCaptured() {
        return isCaptured;
    }

    public void setCaptured(boolean captured) {
        isCaptured = captured;
    }

    @JsonGetter("color")
    public Color getColor() {
        return color;
    }

    @JsonSetter("color")
    public void setColor(Color color) {
        this.color = color;
    }

    @JsonSetter("pieceType")
    public void setType(Type type) {
        this.type = type;
    }

    @JsonGetter("pieceType")
    public Type getType() {
        return type;
    }

    /*
    public int[][] getAllowedMovementDeltas() {
        return allowedMovementDeltas;
    }
     */

}
