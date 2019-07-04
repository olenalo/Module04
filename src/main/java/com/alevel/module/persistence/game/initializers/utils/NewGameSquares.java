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

import static com.alevel.module.persistence.chessboard.configs.Rank.ONE;
import static com.alevel.module.persistence.chessboard.configs.Rank.TWO;
import static com.alevel.module.persistence.chessboard.configs.Rank.SEVEN;
import static com.alevel.module.persistence.chessboard.configs.Rank.EIGHT;

import static com.alevel.module.persistence.piece.configs.Color.BLACK;
import static com.alevel.module.persistence.piece.configs.Color.WHITE;

public class NewGameSquares {

    private List<Square> squares = new ArrayList<>();

    public NewGameSquares() {
        // TODO DRY (see `EmptySquares`)
        for (Rank rank : Rank.values()) {
            for (File file : File.values()) {
                squares.add(new Square(new Space(file, rank)));
            }
        }
    }

    private void placeKingRow(Square square) {
        switch (square.getSpace().getFile()) {
            case A:
                square.setPiece(new Rook());
            case B:
                square.setPiece(new Knight());
            case C:
                square.setPiece(new Bishop());
            case D:
                square.setPiece(new Queen());
            case E:
                square.setPiece(new King());
            case F:
                square.setPiece(new Bishop());
            case G:
                square.setPiece(new Knight());
            case H:
                square.setPiece(new Rook());
        }
    }


    public List<Square> placePieces() {
        for (Square square : this.squares) {
            if (square.getSpace().getRank() == ONE) {
                placeKingRow(square);
                // TODO handle nullables "better" (here and in other cases)
                if (square.getPiece() != null) {
                    square.getPiece().setColor(WHITE);
                }
            }
            if (square.getSpace().getRank() == TWO) {
                square.setPiece(new Pawn());
                if (square.getPiece() != null) {
                    square.getPiece().setColor(WHITE);
                }
            }
            if (square.getSpace().getRank() == SEVEN) {
                square.setPiece(new Pawn());
                if (square.getPiece() != null) {
                    square.getPiece().setColor(BLACK);
                }
            }
            if (square.getSpace().getRank() == EIGHT) {
                placeKingRow(square);
                if (square.getPiece() != null) {
                    square.getPiece().setColor(BLACK);
                }
            }
        }
        return squares;
    }
}
