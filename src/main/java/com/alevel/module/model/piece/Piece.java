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
    @JsonProperty("color")
    private Color color;
    @JsonProperty("type")
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
        int fileDelta = FILE_NUMERIC_DECODER.get(move.getCurrentSpace().getFile()) -
                FILE_NUMERIC_DECODER.get(move.getDestinationSpace().getFile());
        int rankDelta = RANK_NUMERIC_DECODER.get(move.getCurrentSpace().getRank()) -
                RANK_NUMERIC_DECODER.get(move.getDestinationSpace().getRank());
        for (int[] allowedMovesDelta : allowedMovementDeltas) {
            int allowedFileDelta = allowedMovesDelta[0];
            int allowedRankDelta = allowedMovesDelta[1];
            if (fileDelta == allowedFileDelta && rankDelta == allowedRankDelta) {
                return true;
            }
        }
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

    @JsonSetter("type")
    public void setType(Type type) {
        this.type = type;
    }

    @JsonGetter("type")
    public Type getType() {
        return type;
    }

    /*
    public int[][] getAllowedMovementDeltas() {
        return allowedMovementDeltas;
    }
     */

}
