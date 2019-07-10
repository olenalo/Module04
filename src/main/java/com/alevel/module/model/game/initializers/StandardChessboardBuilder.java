package com.alevel.module.model.game.initializers;

import com.alevel.module.model.chessboard.Chessboard;
import com.alevel.module.model.chessboard.Move;
import com.alevel.module.model.chessboard.Square;
import com.alevel.module.model.game.initializers.utils.ChessboardFreshStartPopulator;
import com.alevel.module.model.piece.pieces.King;
import com.alevel.module.model.piece.pieces.Rook;

import java.util.ArrayList;
import java.util.List;

/**
 * Build up a chessboard.
 *
 * Occupied squares go first.
 */
public class StandardChessboardBuilder implements ChessboardBuilder {
    private List<Move> gameMoves;
    private List<Square> movesSquares = new ArrayList<>();

    public StandardChessboardBuilder(List<Move> gameMoves) {
        // TODO Define and set a color to each move's piece (by player);
        //  pass player-color mapping from the controller
        this.gameMoves = gameMoves;
    }

    // TODO Define what pieces are captured (isCaptured) by re-running shouldCapturePiece validation,
    //  and update pieces' isCaptured state
    // TODO Update `isMoved` for Rook and King (castling)
    /**
     * Build up states from moves history.
     *
     * That is, update squares' states move by move.
     * Clean up previous states and add new ones.
     *
     * Update pieces states as well, i.e. `isMoved` for Rook and King
     * (for the sake of castling), `isCaptured`.
     *
     * @return builder object.
     */
    public StandardChessboardBuilder buildUpSquares() {
        System.out.println("============ Moves ================");
        if (!gameMoves.isEmpty()) {
            for (Move m : gameMoves) {
                // TODO update squares (clean up previous state, add new ones).
                System.out.println(m);
                // Clean up a previous state
                movesSquares.remove(new Square(m.getCurrentSpace(), m.getPiece()));
                // Update piece's states
                m.getPiece().setMoved(true);
                // TODO check if `isCaptured`, update if needed
                // Add a new state
                movesSquares.add(new Square(m.getDestinationSpace(), m.getPiece()));  // TODO handle nullables
            }
        }
        System.out.println("============================");
        return this;
    }

    @Override
    public Chessboard build() {
        List<Square> squares;
        squares = new ChessboardFreshStartPopulator().populateFreshStartSquares();
        if (!this.gameMoves.isEmpty()) {  // TODO check against null
            buildUpSquares();
        }
        return new Chessboard(squares);
    }
}
