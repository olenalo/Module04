package com.alevel.module.persistence.game.initializers;

import com.alevel.module.persistence.chessboard.Chessboard;
import com.alevel.module.persistence.chessboard.Square;
import com.alevel.module.persistence.game.initializers.utils.ChessboardSquarePopulator;
import com.alevel.module.persistence.game.initializers.utils.ChessboardPiecePopulator;

import java.util.List;

// TODO refactor populators (subclass from the same base class)
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
            this.emptySquares = new ChessboardSquarePopulator().excludeSquares(this.occupiedSquares);
        }
        return this;
    }

    @Override
    public Chessboard build() {
        List<Square> squares;
        if (this.occupiedSquares.isEmpty()) {  // TODO check against null
            squares = new ChessboardPiecePopulator().populateSquares();
        } else {
            squares = occupiedSquares;
            squares.addAll(emptySquares);
        }
        return new Chessboard(squares);
    }
}
