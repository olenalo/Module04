package com.alevel.module.persistence.game;

import com.alevel.module.persistence.chessboard.Space;
import com.alevel.module.persistence.piece.Piece;
import com.alevel.module.persistence.piece.configs.Color;

import java.util.UUID;

public class Player {

    private String username;
    private Color piecesColor;

    public Player(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    // TODO chess color

    // TODO hashcode, equals

    // TODO piece or piece (castling)?
    public boolean makeMove(Piece piece, Space space) {
        // TODO validate (validation logic to be placed in the `Move` class
        // TODO isCheckmate
        return true;
    }

    public Color getPiecesColor() {
        return piecesColor;
    }

    public void setPiecesColor(Color piecesColor) {
        this.piecesColor = piecesColor;
    }
}
