package com.alevel.module.persistence.game;

import com.alevel.module.persistence.chessboard.Chessboard;

import java.util.UUID;

public class Game {

    // startTimestamp
    // finishTimestamp
    private Player firstPlayer;
    private Player secondPlayer;
    private Chessboard chessboard;
    private boolean isOver = false;  // won't be stored in a db

    public Game(Player firstPlayer, Player secondPlayer, Chessboard chessboard) {
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
        this.chessboard = chessboard;
    }

    public Player getFirstPlayer() {
        return firstPlayer;
    }

    public Player getSecondPlayer() {
        return secondPlayer;
    }

    public Chessboard getChessboard() {
        return chessboard;
    }
}
