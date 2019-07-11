package com.alevel.module.model.chessboard;

import com.alevel.module.model.chessboard.configs.File;
import com.alevel.module.model.chessboard.configs.Rank;
import com.alevel.module.model.piece.Piece;
import com.alevel.module.model.piece.configs.Color;
import com.alevel.module.model.piece.pieces.King;

import java.util.*;

import static com.alevel.module.model.chessboard.configs.FileNumericDecoder.FILE_NUMERIC_DECODER;
import static com.alevel.module.model.chessboard.configs.RankNumericDecoder.RANK_NUMERIC_DECODER;
import static com.alevel.module.model.chessboard.configs.utils.DecodersLookups.getFileKey;
import static com.alevel.module.model.chessboard.configs.utils.DecodersLookups.getRankKey;
import static com.alevel.module.model.piece.configs.rules.MovementRules.*;

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

    public Chessboard(List<Square> squares) {
        this.squares = squares; // TODO Collections.unmodifiableList(squares);
    }

    /**
     *  Check if a destination space is available.
     *
     *  That is, if there's no piece of same color
     *  on the destination space.
     *
     * @param space space to validate (destination space).
     * @return validation results.
     */
    private boolean isAvailable(Space space, Color color) {
        for (Square square : squares) {
            if (space.getFile() == square.getSpace().getFile() &&
                    space.getRank() == square.getSpace().getRank() &&
                    square.getPiece() != null) {
                if (square.getPiece().getColor() == color) {
                    return false;
                }
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

    private List<Square> getSquaresByPieceColor(Color color) {
        List<Square> squaresByColor = new ArrayList<>();
        for (Square square : squares) {
            if (square.getPiece() != null) {
                if (square.getPiece().getColor() == color) {
                    squaresByColor.add(square);
                }
            }
        }
        return squaresByColor;
    }

    private List<Space> checkAllowedMoves(Piece piece) {
        List<Space> allowedSpaces = new ArrayList<>();
        Space space = this.getSpace(piece);
        //  As per general rules, evaluate each possible destination (isAvailable)
        //  TODO do specific (e.g. castling) rules apply here?
        if (space != null) {
            int currentFile = FILE_NUMERIC_DECODER.get(space.getFile());
            int currentRank = RANK_NUMERIC_DECODER.get(space.getRank());
            for (int[] rule : piece.getAllowedMovementDeltas()) {
                File file = getFileKey(FILE_NUMERIC_DECODER, currentFile + rule[0]);
                Rank rank = getRankKey(RANK_NUMERIC_DECODER, currentRank + rule[1]);
                if (file != null && rank != null) {
                    Space s = new Space(file, rank);
                    if (isAvailable(s, piece.getColor())) {
                        allowedSpaces.add(s);
                    }
                }
            }
        }
        for (Space s: allowedSpaces) {
            System.out.println(piece.getColor() + " " + piece.getType() + "'s allowed move: " + s);
        }
        return allowedSpaces;
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
        // Check all possible moves for a king if any
        // Locate a king of opponent's pieces color (find its current space)
        Color currentColor = move.getPiece().getColor();
        Piece king = new King(currentColor.getOpponentColor());
        List<Space> kingAllowedSpaces = checkAllowedMoves(king);
        // If none, mate. Not sure if it's possible to reach this situation in chess game though
        if (kingAllowedSpaces.isEmpty()) {
            return false;
        }

        // Check that with all allowed moves a king would fall under attack
        // Assume a move under evaluation is made (update the chessboard states - no longer immutable!)
        // TODO make chessboard immutable again to preserve history (think of better ways to count a current move);
        //  consider using pieces' vectors to avoid chessboard update
        updateChessboard(move);
        // Iterate over all pieces on the board and
        //  check if king's destination space (within each king's allowed move) is within the reach of any piece,
        //  and if so, checkmate!
        for (Square square: getSquaresByPieceColor(currentColor)) {
            Space spc = square.getSpace();
            int currentFile = FILE_NUMERIC_DECODER.get(spc.getFile());
            int currentRank = RANK_NUMERIC_DECODER.get(spc.getRank());
            // TODO take into account specific rules
            for (int[] rule : square.getPiece().getAllowedMovementDeltas()) {
                File file = getFileKey(FILE_NUMERIC_DECODER, currentFile + rule[0]);
                Rank rank = getRankKey(RANK_NUMERIC_DECODER, currentRank + rule[1]);
                // TODO consider changing loop nesting (by king-allowed-space and then by square)
                for (Space s : kingAllowedSpaces) {
                    // Check if a king is within reach (under attack)
                    if (file == s.getFile() && rank == s.getRank()) {
                        System.out.println("Revealed a piece that can beat the king: " + square.getPiece());
                        return true;
                    }
                }
            }
        }
        return false;
    }

    // TODO DRY
    private void updateChessboard(Move move) {
        squares.remove(new Square(move.getCurrentSpace()));
        squares.add(new Square(move.getCurrentSpace()));
        // Update piece's states
        move.getPiece().setMoved(true);
        // TODO Build pieces' vectors?
        // Clean up a destination square. Like that, we remove captured pieces.
        squares.remove(new Square(move.getDestinationSpace()));
        // Add a new state
        squares.add(new Square(move.getDestinationSpace(), move.getPiece()));
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
