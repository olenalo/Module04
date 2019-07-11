package com.alevel.module.model.chessboard;

import com.alevel.module.model.chessboard.configs.File;
import com.alevel.module.model.chessboard.configs.Rank;
import com.alevel.module.model.piece.Piece;
import com.alevel.module.model.piece.pieces.King;

import java.util.*;

import static com.alevel.module.model.chessboard.configs.FileNumericDecoder.FILE_NUMERIC_DECODER;
import static com.alevel.module.model.chessboard.configs.RankNumericDecoder.RANK_NUMERIC_DECODER;
import static com.alevel.module.model.chessboard.configs.utils.DecodersLookups.getFileKey;
import static com.alevel.module.model.chessboard.configs.utils.DecodersLookups.getRankKey;
import static com.alevel.module.model.piece.configs.rules.MovementRules.KING_ALLOWED_MOVEMENT_DELTAS;

/**
 * Build up a board state from squares.
 *
 * Store states to provide the access to them.
 * Occupied squares go first.
 *
 * TODO Iteration should stop once all occupied squares are iterated over
 *  i.e. once an empty square appears.
 */
public class Chessboard {
    // TODO update JPA entity classes (consider replacing Long with UUID everywhere else)
    private final UUID id  = UUID.randomUUID();

    private List<Square> squares;

    // TODO capturedPieces to track a score

    public Chessboard(List<Square> squares) {
        this.squares = Collections.unmodifiableList(squares);
    }

    /**
     *  Check if a destination square is empty.
     *
     *  Piece color does not matter.
     *
     * @param space space to validate.
     * @return validation results.
     */
    private boolean isEmpty(Space space) {
        for (Square square : squares) {
            if (space.getFile() == square.getSpace().getFile() &&
                    space.getRank() == square.getSpace().getRank() &&
                    square.getPiece() != null) {
                return false;
            }
        }
        return true;
    }

    /**
     * Locate a piece.
     *
     * First occurrence is returned.
     *
     * @param piece a piece to locate.
     * @return space where a piece is located.
     */
    private Space getSpace(Piece piece) {
        for (Square square : squares) {
            if (square.getPiece() != null) {
                if (square.getPiece().equals(piece)) {
                    return square.getSpace();
                }
            }
        }
        return null;
    }

    /**
     * Check if checkmate is in effect.
     *
     * That is, check if the game is won.
     *
     * Check if a king will no longer be able to move
     * to not fall under attack if a move is made.
     *
     * @return boolean: true if checkmate is reached, false if not.
     */
    public boolean isCheckMate(Move move) {
        // Check all possible moves for a king (if any; TODO if none, mate? is it possible in chess game?)
        // Locate a king of opponent's pieces color (find its current space)
        List<Space> allowedSpaces = new ArrayList<>();
        Space kingSpace = this.getSpace(new King(move.getPiece().getColor().getOpponentColor()));
        //  As per general rules, evaluate each possible destination (isEmpty)
        //  TODO do specific (castling) rules apply here?
        if (kingSpace != null) {
            int currentFile = FILE_NUMERIC_DECODER.get(kingSpace.getFile());
            int currentRank = RANK_NUMERIC_DECODER.get(kingSpace.getRank());
            for (int[] rule : KING_ALLOWED_MOVEMENT_DELTAS) {
                File file = getFileKey(FILE_NUMERIC_DECODER, currentFile + rule[0]);
                Rank rank = getRankKey(RANK_NUMERIC_DECODER, currentRank + rule[1]);
                if (file != null && rank != null) {
                    Space space = new Space(file, rank);
                    if (isEmpty(space)) {
                        allowedSpaces.add(space);
                    }
                }
            }
        }

        // TODO Check that with all allowed moves a king would fall under attack, i.e.
        //  assume a move under evaluation is made (how? with immutable chessboard...),
        //  iterate over all pieces on the board, (do we even need to iterate?),
        //  check if king's destination space (within each king's allowed move) is within the reach of any piece,
        //  if so, baaaaam!
        for (Space s: allowedSpaces) {
            System.out.println(move.getPiece().getColor().getOpponentColor() + " king's allowed move: " + s);
        }

        return false;
    }

    public List<Square> getSquares() {
        return squares;
    }

    public void setSquares(List<Square> squares) {
        this.squares = squares;
    }

    @Override
    public String toString() {
        return "Chessboard{" +
                "squares=" + squares +
                '}';
    }
}
