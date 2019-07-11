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
        this.squares = squares; // Collections.unmodifiableList(squares);
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
        List<Space> kingAllowedSpaces = new ArrayList<>();
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
                        kingAllowedSpaces.add(space);
                    }
                }
            }
        }
        for (Space s: kingAllowedSpaces) {
            System.out.println(move.getPiece().getColor().getOpponentColor() + " king's allowed move: " + s);
        }

        // Check that with all allowed moves a king would fall under attack
        // Assume a move under evaluation is made (update the chessboard states - no longer immutable!)
        // TODO make chessboard immutable again to preserve history (think of better ways to count a current move)
        updateChessboard(move);
        // Iterate over all pieces on the board and
        //  check if king's destination space (within each king's allowed move) is within the reach of any piece,
        //  and if so, checkmate!
        for (Square square: squares) {
            if (square.getPiece() != null) {
                if (square.getPiece().getColor() == move.getPiece().getColor()) {
                    Space space = square.getSpace(); // TODO filter out by color
                    int currentFile = FILE_NUMERIC_DECODER.get(space.getFile());
                    int currentRank = RANK_NUMERIC_DECODER.get(space.getRank());
                    // TODO take into account specific rules
                    System.out.println("o_O o_O o_O o_O o_O " + square.getPiece() + " at " + square.getSpace());
                    /*
                    for (int [] rule : square.getPiece().getAllowedMovementDeltas() ) {
                        System.out.println("o_O o_O o_O  " + rule[0] + " and " + rule[1]);
                    }
                    // System.out.println("o_O o_O o_O  " + square.getPiece().getAllowedMovementDeltas() != null);
                     */
                    for (int[] rule : square.getPiece().getAllowedMovementDeltas()) { // FIXME deltas
                        File file = getFileKey(FILE_NUMERIC_DECODER, currentFile + rule[0]);
                        Rank rank = getRankKey(RANK_NUMERIC_DECODER, currentRank + rule[1]);
                        // TODO consider changing loop nesting (by allowed space and then by square)
                        for (Space s : kingAllowedSpaces) {
                            // Check if a king is within reach (under attack)
                            if (file == s.getFile() && rank == s.getRank()) {
                                System.out.println("Revealed a piece which can beat the king: " + square.getPiece());
                                return true;
                            }
                        }
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
        System.out.println("0000000000000000000000000000000000000000" + move.getPiece());
        System.out.println("0000000000000000000000000000000000000000" + move.getPiece().getType());
        System.out.println("0000000000000000000" + move.getPiece().getAllowedMovementDeltas());
        // FIXME come up with a better design
        switch(move.getPiece().getType()) {
            // TODO consider getting rid of magic strings
            case KING:
                move.getPiece().setAllowedMovementDeltas(KING_ALLOWED_MOVEMENT_DELTAS);
                break;
            case BISHOP:
                move.getPiece().setAllowedMovementDeltas(BISHOP_ALLOWED_MOVEMENT_DELTAS);
                break;
            case KNIGHT:
                move.getPiece().setAllowedMovementDeltas(KNIGHT_ALLOWED_MOVEMENT_DELTAS);
                break;
            case PAWN:
                move.getPiece().setAllowedMovementDeltas(PAWN_ALLOWED_MOVEMENT_DELTAS);
                break;
            case QUEEN:
                move.getPiece().setAllowedMovementDeltas(QUEEN_ALLOWED_MOVEMENT_DELTAS);
                break;
            case ROOK:
                move.getPiece().setAllowedMovementDeltas(ROOK_ALLOWED_MOVEMENT_DELTAS);
                break;
        }
        // TODO Build pieces' vectors?
        // Clean up a destination square. Like that, we remove captured pieces.
        squares.remove(new Square(move.getDestinationSpace()));
        // Add a new state
        squares.add(new Square(move.getDestinationSpace(), move.getPiece()));  // TODO handle nullables
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
