package com.alevel.module.model.piece;

import com.alevel.module.controller.exceptions.InvalidMoveException;
import com.alevel.module.model.chessboard.Chessboard;
import com.alevel.module.model.chessboard.Move;
import com.alevel.module.model.chessboard.Space;
import com.alevel.module.model.chessboard.configs.Rank;
import com.alevel.module.model.piece.Vector;
import com.alevel.module.model.piece.configs.Color;
import com.alevel.module.model.piece.configs.Type;
import com.alevel.module.model.piece.pieces.King;
import com.alevel.module.model.piece.pieces.Bishop;
import com.alevel.module.model.piece.pieces.Knight;
import com.alevel.module.model.piece.pieces.Pawn;
import com.alevel.module.model.piece.pieces.Queen;
import com.alevel.module.model.piece.pieces.Rook;
import com.fasterxml.jackson.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.alevel.module.model.chessboard.configs.FileNumericDecoder.FILE_NUMERIC_DECODER;
import static com.alevel.module.model.chessboard.configs.RankNumericDecoder.RANK_NUMERIC_DECODER;
import static com.alevel.module.model.chessboard.configs.utils.DecodersLookups.getFileKey;
import static com.alevel.module.model.chessboard.configs.utils.DecodersLookups.getRankKey;

@JsonIgnoreProperties({"moved", "allowedMovementDeltas", "vector"})
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
    protected Color color;
    @JsonProperty("pieceType")  // FIXME this is an ugly workaround; need to pass both "type" and "pieceType" with JSON
    protected Type type;
    protected int[][] allowedMovementDeltas;
    private boolean isMoved = false;  // Actually needed for King and Rook only (for castling)
    private Vector vector;

    public int[][] getAllowedMovementDeltas() {
        return allowedMovementDeltas;
    }

    // TODO: add additional rules,
    //    i.e. if special features are available
    //    (promotion, castling), specific rules apply
    // private int[][] getAllowedSpecificMovesDeltas;

    public boolean isMoved() {
        return isMoved;
    }

    public void setMoved(boolean moved) {
        isMoved = moved;
    }

    // Ref.: https://stackoverflow.com/a/51014378
    public Piece() {}

    public Piece(Color color, Type type) {
        this.color = color;
        this.type = type;
    }

    public boolean validateMove(Move move, Chessboard chessboard) throws InvalidMoveException {
        this.setVector(move);
        return validateMoveDeltaExists(move) &&
                validatePerMovementRules(move) &&
                chessboard.validateMove(move);
    }

    private boolean validateMoveDeltaExists(Move move) {
        int expectedFileDelta = FILE_NUMERIC_DECODER.get(move.getDestinationSpace().getFile()) -
                FILE_NUMERIC_DECODER.get(move.getCurrentSpace().getFile());
        int expectedRankDelta = RANK_NUMERIC_DECODER.get(move.getDestinationSpace().getRank()) -
                RANK_NUMERIC_DECODER.get(move.getCurrentSpace().getRank());
        return expectedFileDelta != 0 || expectedRankDelta != 0;
    }

    /**
     * Validate if a move is allowed.
     *
     * @param move move to validate.
     * @return true if a move is valid, otherwise false.
     */
    private boolean validatePerMovementRules(Move move) throws InvalidMoveException {
        int expectedFileDelta = FILE_NUMERIC_DECODER.get(move.getDestinationSpace().getFile()) -
                FILE_NUMERIC_DECODER.get(move.getCurrentSpace().getFile());
        int expectedRankDelta = RANK_NUMERIC_DECODER.get(move.getDestinationSpace().getRank()) -
                RANK_NUMERIC_DECODER.get(move.getCurrentSpace().getRank());
        for (int[] allowedMovesDelta : allowedMovementDeltas) {
            if (expectedFileDelta == allowedMovesDelta[0] && expectedRankDelta == allowedMovesDelta[1]) {
                return true;
            }
        }
        // TODO add specific rules validation (if any): will apply for King, Rook, and Pawn
        return false;
    }

    public Vector getVector() {
        return vector;
    }

    void setVector(Vector vector) {
        this.vector = vector;
    }

    /**
     * Set vector of perspective spaces.
     *
     * Does not include a current space.
     *
     * @param move
     */
    void setVector(Move move) {
        List<Space> spaces = new ArrayList<>();

        Space currentSpace = move.getCurrentSpace();
        Space destinationSpace = move.getDestinationSpace();

        int expectedFileDelta = FILE_NUMERIC_DECODER.get(destinationSpace.getFile()) -
                FILE_NUMERIC_DECODER.get(currentSpace.getFile());
        int expectedRankDelta = RANK_NUMERIC_DECODER.get(destinationSpace.getRank()) -
                RANK_NUMERIC_DECODER.get(currentSpace.getRank());

        // TODO consider refactoring (although it's already quite readable)
        if (expectedFileDelta == 0) {
            if (expectedRankDelta > 0) {
                for (int i = 1; i <= expectedRankDelta; i++) {
                    int expectedRank = RANK_NUMERIC_DECODER.get(currentSpace.getRank()) + i;
                    spaces.add(new Space(destinationSpace.getFile(), getRankKey(RANK_NUMERIC_DECODER, expectedRank)));
                }
            } else if (expectedRankDelta < 0) {
                for (int i = -1; i >= expectedRankDelta; i--) {
                    int expectedRank = RANK_NUMERIC_DECODER.get(currentSpace.getRank()) - Math.abs(i);
                    spaces.add(new Space(destinationSpace.getFile(), getRankKey(RANK_NUMERIC_DECODER, expectedRank)));
                }
            }
        }
        else if (expectedRankDelta == 0) {
            if (expectedFileDelta > 0) {
                for (int i = 1; i <= expectedFileDelta; i++) {
                    int expectedFile = FILE_NUMERIC_DECODER.get(currentSpace.getFile()) + i;
                    spaces.add(new Space(getFileKey(FILE_NUMERIC_DECODER, expectedFile), destinationSpace.getRank()));
                }
            } else {
                for (int i = -1; i >= expectedFileDelta; i--) {
                    int expectedFile = FILE_NUMERIC_DECODER.get(currentSpace.getFile()) - Math.abs(i);
                    spaces.add(new Space(getFileKey(FILE_NUMERIC_DECODER, expectedFile), destinationSpace.getRank()));
                }
            }
        }
        // Diagonal
        else if (expectedFileDelta > 0) {
            if (expectedRankDelta > 0) {
                for (int i = 1; i <= expectedFileDelta; i++) {
                    int expectedFile = FILE_NUMERIC_DECODER.get(currentSpace.getFile()) + i;
                    int expectedRank = RANK_NUMERIC_DECODER.get(currentSpace.getRank()) + i;
                    spaces.add(new Space(getFileKey(FILE_NUMERIC_DECODER, expectedFile), getRankKey(RANK_NUMERIC_DECODER, expectedRank)));
                }
            } else {
                for (int i = -1; i >= expectedRankDelta; i--) {
                    int expectedFile = FILE_NUMERIC_DECODER.get(currentSpace.getFile()) + Math.abs(i);
                    int expectedRank = RANK_NUMERIC_DECODER.get(currentSpace.getRank()) - Math.abs(i);
                    spaces.add(new Space(getFileKey(FILE_NUMERIC_DECODER, expectedFile), getRankKey(RANK_NUMERIC_DECODER, expectedRank)));
                }
            }
        }
        // Diagonal
        else if (expectedFileDelta < 0) {
            if (expectedRankDelta > 0) {
                for (int i = -1; i >= expectedFileDelta; i--) {
                    int expectedFile = FILE_NUMERIC_DECODER.get(currentSpace.getFile()) - Math.abs(i);
                    int expectedRank = RANK_NUMERIC_DECODER.get(currentSpace.getRank()) + Math.abs(i);
                    spaces.add(new Space(getFileKey(FILE_NUMERIC_DECODER, expectedFile), getRankKey(RANK_NUMERIC_DECODER, expectedRank)));
                }
            } else {
                for (int i = -1; i >= expectedFileDelta; i--) {
                    int expectedFile = FILE_NUMERIC_DECODER.get(currentSpace.getFile()) - Math.abs(i);
                    int expectedRank = RANK_NUMERIC_DECODER.get(currentSpace.getRank()) - Math.abs(i);
                    spaces.add(new Space(getFileKey(FILE_NUMERIC_DECODER, expectedFile), getRankKey(RANK_NUMERIC_DECODER, expectedRank)));
                }
            }
        }

        this.vector = new Vector(spaces);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piece piece = (Piece) o;
        return color == piece.color &&
                type == piece.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, type);
    }

}
