package com.alevel.module.persistence.game.initializers.utils;

import com.alevel.module.persistence.chessboard.Space;
import com.alevel.module.persistence.chessboard.Square;
import com.alevel.module.persistence.chessboard.configs.File;
import com.alevel.module.persistence.chessboard.configs.Rank;

import java.util.ArrayList;
import java.util.List;

public class ChessboardSquarePopulator {

    private List<Square> squares = new ArrayList<>();

    public ChessboardSquarePopulator() {
        for (Rank rank : Rank.values()) {
            for (File file : File.values()) {
                squares.add(new Square(new Space(file, rank)));
            }
        }
    }

    public List<Square> excludeSquares(List<Square> squares) {
        // Use case: exclude occupied squares
        for (Square square : squares) {
            this.squares.remove(square);
        }
        return this.squares;
    }

    public List<Square> getSquares() {
        return squares;
    }
}
