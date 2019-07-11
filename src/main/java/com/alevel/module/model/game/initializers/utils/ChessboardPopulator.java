package com.alevel.module.model.game.initializers.utils;

import com.alevel.module.model.chessboard.Space;
import com.alevel.module.model.chessboard.Square;
import com.alevel.module.model.chessboard.configs.File;
import com.alevel.module.model.chessboard.configs.Rank;
import com.alevel.module.model.piece.configs.Color;
import com.alevel.module.model.piece.pieces.Bishop;
import com.alevel.module.model.piece.pieces.King;
import com.alevel.module.model.piece.pieces.Knight;
import com.alevel.module.model.piece.pieces.Pawn;
import com.alevel.module.model.piece.pieces.Queen;
import com.alevel.module.model.piece.pieces.Rook;

import java.util.ArrayList;
import java.util.List;

import static com.alevel.module.model.piece.configs.Color.BLACK;
import static com.alevel.module.model.piece.configs.Color.WHITE;

public class ChessboardPopulator {

    private List<Square> squares = new ArrayList<>();

    public ChessboardPopulator() {
        for (Rank rank : Rank.values()) {
            for (File file : File.values()) {
                squares.add(new Square(new Space(file, rank)));
            }
        }
    }

    private void populateKingRowSquare(Square square, Color color) {
        switch (square.getSpace().getFile()) {
            case A:
            case H:
                square.setPiece(new Rook(color));
                break;
            case B:
            case G:
                square.setPiece(new Knight(color));
                break;
            case C:
            case F:
                square.setPiece(new Bishop(color));
                break;
            case D:
                square.setPiece(new Queen(color));
                break;
            case E:
                square.setPiece(new King(color));
                break;
        }
    }

    public List<Square> populateFreshStartSquares() {
        for (Square square : this.squares) {
            switch (square.getSpace().getRank()) {
                case ONE:
                    populateKingRowSquare(square, WHITE);
                    break;
                case TWO:
                    square.setPiece(new Pawn(WHITE));
                    break;
                case SEVEN:
                    square.setPiece(new Pawn(BLACK));
                    break;
                case EIGHT:
                    populateKingRowSquare(square, BLACK);
                    break;
            }
        }
        return squares;
    }
}
