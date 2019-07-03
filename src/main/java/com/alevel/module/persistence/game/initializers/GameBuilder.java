package com.alevel.module.persistence.game.initializers;

import com.alevel.module.persistence.chessboard.Chessboard;
import com.alevel.module.persistence.game.Game;
import com.alevel.module.persistence.game.Player;

public abstract class GameBuilder {
    // There might be more than two players in "non-standard" games :)
    protected Chessboard chessboard;

    public abstract Game start();

}
