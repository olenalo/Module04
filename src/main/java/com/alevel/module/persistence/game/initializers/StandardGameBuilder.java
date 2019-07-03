package com.alevel.module.persistence.game.initializers;

import com.alevel.module.persistence.chessboard.Chessboard;
import com.alevel.module.persistence.chessboard.Square;
import com.alevel.module.persistence.game.Game;
import com.alevel.module.persistence.game.Player;

import java.util.List;

import static com.alevel.module.persistence.piece.configs.Color.WHITE;
import static com.alevel.module.persistence.piece.configs.Color.BLACK;

public class StandardGameBuilder extends GameBuilder {

    protected Player firstPlayer;
    protected Player secondPlayer;

    public StandardGameBuilder(Player firstPlayer) {
        this.firstPlayer = firstPlayer;
        // We'll then wait for the second player
        // whose piece will be assigned black color
        firstPlayer.setPiecesColor(WHITE);
    }

    public StandardGameBuilder assignSecondPlayer(Player player) {
        this.secondPlayer = player;
        secondPlayer.setPiecesColor(BLACK);
        return this;
    }

    public StandardGameBuilder buildChessboard(List<Square> squares) {
        this.chessboard = new Chessboard(squares);
        return this;
    }

    @Override
    public Game start() {
        return new Game(this.firstPlayer,
                        this.secondPlayer,
                        this.chessboard);
    }

}
