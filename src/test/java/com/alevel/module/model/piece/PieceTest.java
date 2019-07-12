package com.alevel.module.model.piece;

import com.alevel.module.model.chessboard.Chessboard;
import com.alevel.module.model.chessboard.Move;
import com.alevel.module.model.chessboard.Space;
import com.alevel.module.model.game.Game;
import com.alevel.module.model.game.Player;
import com.alevel.module.model.game.initializers.StandardChessboardBuilder;
import com.alevel.module.model.piece.pieces.King;
import com.alevel.module.model.piece.pieces.Pawn;
import com.alevel.module.model.piece.pieces.Queen;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.alevel.module.model.chessboard.configs.File.*;
import static com.alevel.module.model.chessboard.configs.File.G;
import static com.alevel.module.model.chessboard.configs.Rank.*;
import static com.alevel.module.model.piece.configs.Color.BLACK;
import static com.alevel.module.model.piece.configs.Color.WHITE;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PieceTest {

    private Player firstPlayer = new Player(1L);
    private Player secondPlayer = new Player(2L);
    private Game game = new Game(1L, firstPlayer, secondPlayer);
    private List<Move> gameMoves = new ArrayList<>();

    @After
    public void tearDown() {
        gameMoves.clear();
    }

    // TODO parametrize it (moves history, current move, validation result)
    @Test
    public void testValidateMove() {
        // Prerequisites TODO DRY
        gameMoves.add(new Move(new Pawn(WHITE), new Space(F, TWO), new Space(F, THREE), firstPlayer));
        gameMoves.add(new Move(new Pawn(BLACK), new Space(E, SEVEN), new Space(E, FIVE), secondPlayer));
        gameMoves.add(new Move(new Pawn(WHITE), new Space(G, TWO), new Space(G, FOUR), firstPlayer));
        Chessboard chessboard = new StandardChessboardBuilder(gameMoves, game).build();
        // Expected move for checkmate (fool's mate)
        Move currentMove = new Move(new Queen(BLACK), new Space(D, EIGHT), new Space(H, FOUR), secondPlayer);
        currentMove.setGame(game);
        assertTrue(currentMove.getPiece().validateMove(currentMove, chessboard));
    }
}
