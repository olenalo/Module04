package com.alevel.module.persistence.game.initializers;

import com.alevel.module.persistence.chessboard.Chessboard;
import com.alevel.module.persistence.chessboard.Square;
import com.alevel.module.persistence.game.initializers.utils.EmptySquares;
import com.alevel.module.persistence.game.initializers.utils.NewGameSquares;

import java.util.List;

/**
 * Build up a chessboard.
 *
 * Occupied squares go first.
 */
public class StandardChessboardBuilder implements ChessboardBuilder {

    private List<Square> occupiedSquares;
    private List<Square> emptySquares;

    public StandardChessboardBuilder addOccupiedSquares(List<Square> occupiedSquares) {
        this.occupiedSquares = occupiedSquares;
        return this;
    }

    public StandardChessboardBuilder addEmptySquares() {
        if (this.occupiedSquares != null) {
            this.emptySquares = new EmptySquares().excludeSquares(this.occupiedSquares);
        }
        return this;
    }

    @Override
    public Chessboard build() {
        List<Square> squares;
        if (this.occupiedSquares != null) {
            squares = occupiedSquares;
            squares.addAll(emptySquares);
        } else {
            squares = new NewGameSquares().placePieces();
        }
        return new Chessboard(squares);
    }
}
