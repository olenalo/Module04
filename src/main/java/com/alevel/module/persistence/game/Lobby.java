package com.alevel.module.persistence.game;

public class Lobby {

    private Player firstPlayer;
    private Player secondPlayer;
    private Game game;

    // TODO Если пользователь1 не отпинговуется в течении 1 минуты, то считается, что он
    //  покинул лобби и автоматически проиграл. Это значит, что пинговка может
    //  происходить даже тогда, когда ход пользователя1.

    // TODO place matchmaking logic here

    public Lobby(Player firstPlayer) {
        this.firstPlayer = firstPlayer;
    }

    public Player getFirstPlayer() {
        return firstPlayer;
    }

    public Player getSecondPlayer() {
        return secondPlayer;
    }

    public void setSecondPlayer(Player secondPlayer) {
        this.secondPlayer = secondPlayer;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

}
