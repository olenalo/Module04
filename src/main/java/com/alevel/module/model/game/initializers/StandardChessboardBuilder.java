package com.alevel.module.model.game.initializers;

import com.alevel.module.model.chessboard.Chessboard;
import com.alevel.module.model.chessboard.Move;
import com.alevel.module.model.chessboard.Square;
import com.alevel.module.model.game.Game;
import com.alevel.module.model.game.initializers.utils.ChessboardPopulator;

import java.util.ArrayList;
import java.util.List;

import static com.alevel.module.model.piece.configs.Color.BLACK;
import static com.alevel.module.model.piece.configs.Color.WHITE;

/**
 * Build up a chessboard.
 */
public class StandardChessboardBuilder implements ChessboardBuilder {
    private Game game;
    private List<Move> gameMoves;
    private List<Square> movesSquares = new ArrayList<>();

    public StandardChessboardBuilder(List<Move> gameMoves, Game game) {
        this.gameMoves = gameMoves;
        this.game = game;
    }

    /**
     * Build up states from moves history.
     *
     * That is, update squares' states move by move.
     * Clean up previous states and add new ones.
     *
     * Update pieces states as well.
     *
     * TODO split into dedicated methods (not SOLID enough, does too many things)
     *
     * @return builder object.
     */
    private void buildUpStates() {
        System.out.println("============ Moves history: ================");
        if (!gameMoves.isEmpty()) {
            for (Move move : gameMoves) {
                // Clean up a previous state.
                // TODO Consider avoiding removal (just setting a piece to null)
                movesSquares.remove(new Square(move.getCurrentSpace()));
                movesSquares.add(new Square(move.getCurrentSpace()));
                // Update piece's states
                // Define as moved
                move.getPiece().setMoved(true);  // TODO check nullable (shouldn't be null though)
                // Update piece colors
                if (game.getFirstPlayer().getId().equals(move.getPlayer().getId())) {
                    move.getPiece().setColor(WHITE);
                } else {
                    move.getPiece().setColor(BLACK);
                }
                System.out.println(move);
                // TODO Build pieces' vectors
                // Clean up a destination square. Like that, we remove captured pieces.
                movesSquares.remove(new Square(move.getDestinationSpace()));
                // Add a new state
                movesSquares.add(new Square(move.getDestinationSpace(), move.getPiece()));
            }
        }
        System.out.println("============================");
    }

    @Override
    public Chessboard build() {
        this.movesSquares = new ChessboardPopulator().populateFreshStartSquares();
        if (!this.gameMoves.isEmpty()) {
            buildUpStates();
        }
        return new Chessboard(this.movesSquares);
    }
}
