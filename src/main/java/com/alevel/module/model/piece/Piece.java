package com.alevel.module.model.piece;

import com.alevel.module.model.chessboard.Chessboard;
import com.alevel.module.model.chessboard.Move;
import com.alevel.module.model.chessboard.Space;
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

    public abstract boolean validateMove(Move move, Chessboard chessboard);

    public Piece(Color color, Type type) {
        this.color = color;
        this.type = type;
    }

    /**
     * Validate if a move is allowed.
     *
     * @param move move to validate.
     * @return true if a move is valid, otherwise false.
     */
    protected boolean validatePerMovementRules(Move move) {
        int expectedFileDelta = FILE_NUMERIC_DECODER.get(move.getDestinationSpace().getFile()) -
                FILE_NUMERIC_DECODER.get(move.getCurrentSpace().getFile());
        int expectedRankDelta = RANK_NUMERIC_DECODER.get(move.getDestinationSpace().getRank()) -
                RANK_NUMERIC_DECODER.get(move.getCurrentSpace().getRank());
        for (int[] allowedMovesDelta : allowedMovementDeltas) {
            if (expectedFileDelta == allowedMovesDelta[0] && expectedRankDelta == allowedMovesDelta[1]) {
                return true;
            }
        }
        // TODO add specific rules validation
        return false;
    }

    public Vector getVector() {
        return vector;
    }

    public void setVector(Vector vector) {
        this.vector = vector;
    }

    protected void setVector(Move move) {

        Space currentSpace = move.getCurrentSpace();
        Space destinationSpace = move.getDestinationSpace();

        int expectedFileDelta = FILE_NUMERIC_DECODER.get(destinationSpace.getFile()) -
                FILE_NUMERIC_DECODER.get(currentSpace.getFile());
        int expectedRankDelta = RANK_NUMERIC_DECODER.get(destinationSpace.getRank()) -
                RANK_NUMERIC_DECODER.get(currentSpace.getRank());

        // TODO implement

        List<Space> spaces = new ArrayList<>();
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
