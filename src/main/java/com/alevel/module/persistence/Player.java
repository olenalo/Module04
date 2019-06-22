package com.alevel.module.persistence;

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

    // TODO hashcode, equals

    /**
     * Find a lobby.
     *
     * If no lobby with a single player is found,
     * a lobby is created.
     */
    public void findLobby() {
        // TODO
    }

    public boolean makeMove(Piece piece, Cell cell) {
        // TODO validate (validation logic to be placed in the `Move` class
        // TODO isCheckmate
        return true;
    }
}
