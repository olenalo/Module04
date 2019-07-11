package com.alevel.module.model.game.initializers;

import com.alevel.module.model.chessboard.Chessboard;
import com.alevel.module.model.chessboard.Move;
import com.alevel.module.model.chessboard.Square;
import com.alevel.module.model.game.initializers.utils.ChessboardFreshStartPopulator;

import java.util.ArrayList;
import java.util.List;

/**
 * Build up a chessboard.
 */
public class StandardChessboardBuilder implements ChessboardBuilder {
    private List<Move> gameMoves;
    private List<Square> movesSquares = new ArrayList<>();

    public StandardChessboardBuilder(List<Move> gameMoves) {
        // TODO Define and set a color to each move's piece (by player);
        //  pass player-color mapping from the controller
        this.gameMoves = gameMoves;
    }

    /**
     * Build up states from moves history.
     *
     * That is, update squares' states move by move.
     * Clean up previous states and add new ones.
     *
     * Update pieces states as well.
     *
     * @return builder object.
     */
    public StandardChessboardBuilder buildUpSquares(List<Square> squares) {
        System.out.println("============ Moves history: ================");
        if (!gameMoves.isEmpty()) {
            for (Move move : gameMoves) {
                System.out.println(move);
                // Clean up a previous state.
                // TODO Consider avoiding removal (just settting a piece to null)
                squares.remove(new Square(move.getCurrentSpace()));
                squares.add(new Square(move.getCurrentSpace()));
                // Update piece's states
                move.getPiece().setMoved(true);  // TODO check nullable (shouldn't be null though)
                // TODO Build pieces' vectors
                // Clean up a destination square. Like that, we remove captured pieces.
                squares.remove(new Square(move.getDestinationSpace()));
                // Add a new state
                squares.add(new Square(move.getDestinationSpace(), move.getPiece()));
            }
        }
        this.movesSquares = squares;
        System.out.println("============================");
        return this;
    }

    @Override
    public Chessboard build() {
        List<Square> squares;
        squares = new ChessboardFreshStartPopulator().populateFreshStartSquares();
        if (!this.gameMoves.isEmpty()) {
            buildUpSquares(squares);
        }
        return new Chessboard(this.movesSquares);
    }
}
