package com.alevel.module.persistence;

import com.alevel.module.persistence.chessboard.Space;
import com.alevel.module.persistence.pieces.Piece;

public class Player {

    private String username;

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

    // TODO consider moving someplace else
    /**
     * Find a lobby.
     *
     * If no lobby with a single player is found,
     * a lobby is created.
     */
    public void findLobby() {
        // TODO
    }

    // TODO piece or pieces (castling)?
    public boolean makeMove(Piece piece, Space space) {
        // TODO validate (validation logic to be placed in the `Move` class
        // TODO isCheckmate
        return true;
    }
}
