package com.alevel.module.persistence.game;

import com.alevel.module.persistence.chessboard.Chessboard;

public class Game {

    // private Lobby lobby;
    private Player firstPlayer;
    private Player secondPlayer;
    // private Player[] players = new Player[2];
    private Chessboard chessboard;

    // TODO results - null if not over? or intermediate results as well?
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
