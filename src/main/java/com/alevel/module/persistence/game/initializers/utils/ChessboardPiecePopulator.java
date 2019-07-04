package com.alevel.module.persistence.game.initializers.utils;

import com.alevel.module.persistence.chessboard.Space;
import com.alevel.module.persistence.chessboard.Square;
import com.alevel.module.persistence.chessboard.configs.File;
import com.alevel.module.persistence.chessboard.configs.Rank;
import com.alevel.module.persistence.piece.pieces.Bishop;
import com.alevel.module.persistence.piece.pieces.King;
import com.alevel.module.persistence.piece.pieces.Knight;
import com.alevel.module.persistence.piece.pieces.Pawn;
import com.alevel.module.persistence.piece.pieces.Queen;
import com.alevel.module.persistence.piece.pieces.Rook;

import java.util.ArrayList;
import java.util.List;

import static com.alevel.module.persistence.chessboard.configs.File.A;
import static com.alevel.module.persistence.chessboard.configs.File.B;
import static com.alevel.module.persistence.chessboard.configs.File.C;
import static com.alevel.module.persistence.chessboard.configs.File.D;
import static com.alevel.module.persistence.chessboard.configs.File.E;
import static com.alevel.module.persistence.chessboard.configs.File.F;
import static com.alevel.module.persistence.chessboard.configs.File.G;
import static com.alevel.module.persistence.chessboard.configs.File.H;
import static com.alevel.module.persistence.chessboard.configs.Rank.ONE;
import static com.alevel.module.persistence.chessboard.configs.Rank.TWO;
import static com.alevel.module.persistence.chessboard.configs.Rank.SEVEN;
import static com.alevel.module.persistence.chessboard.configs.Rank.EIGHT;

import static com.alevel.module.persistence.piece.configs.Color.BLACK;
import static com.alevel.module.persistence.piece.configs.Color.WHITE;

public class ChessboardPiecePopulator {

    private List<Square> squares = new ArrayList<>();

    public ChessboardPiecePopulator() {
        // TODO DRY (see `ChessboardSquarePopulator`)
        for (Rank rank : Rank.values()) {
            for (File file : File.values()) {
                squares.add(new Square(new Space(file, rank)));
            }
        }
    }

    private void populateKingRowSquare(Square square) {
        switch (square.getSpace().getFile()) {
            case A:
            case H:
                square.setPiece(new Rook());
                break;
            case B:
            case G:
                square.setPiece(new Knight());
                break;
            case C:
            case F:
                square.setPiece(new Bishop());
                break;
            case D:
                square.setPiece(new Queen());
                break;
            case E:
                square.setPiece(new King());
                break;
        }
    }

    public List<Square> populateSquares() {
        for (Square square : this.squares) {
            if (square.getSpace().getRank().equals(ONE)) {
                populateKingRowSquare(square);
                // TODO handle nullables "better" (here and in other cases)
                if (square.getPiece() != null) {
                    square.getPiece().setColor(WHITE);
                }
            }
            if (square.getSpace().getRank().equals(TWO)) {
                square.setPiece(new Pawn());
                if (square.getPiece() != null) {
                    square.getPiece().setColor(WHITE);
                }
            }
            if (square.getSpace().getRank().equals(SEVEN)) {
                square.setPiece(new Pawn());
                if (square.getPiece() != null) {
                    square.getPiece().setColor(BLACK);
                }
            }
            if (square.getSpace().getRank().equals(EIGHT)) {
                populateKingRowSquare(square);
                if (square.getPiece() != null) {
                    square.getPiece().setColor(BLACK);
                }
            }
        }
        return squares;
    }
}
