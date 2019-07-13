package com.alevel.module.model.game;

import com.alevel.module.model.chessboard.Chessboard;
import com.alevel.module.model.chessboard.Move;
import com.alevel.module.model.chessboard.Space;
import com.alevel.module.model.game.initializers.StandardChessboardBuilder;
import com.alevel.module.model.piece.pieces.Bishop;
import com.alevel.module.model.piece.pieces.Pawn;
import com.alevel.module.model.piece.pieces.Queen;
import com.alevel.module.model.piece.pieces.Rook;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static com.alevel.module.model.chessboard.configs.File.*;
import static com.alevel.module.model.chessboard.configs.File.G;
import static com.alevel.module.model.chessboard.configs.Rank.*;
import static com.alevel.module.model.chessboard.configs.Rank.FOUR;
import static com.alevel.module.model.piece.configs.Color.BLACK;
import static com.alevel.module.model.piece.configs.Color.WHITE;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ChessboardTest {

    private Player firstPlayer = new Player(1L);
    private Player secondPlayer = new Player(2L);
    private Game game = new Game(1L, firstPlayer, secondPlayer);
    private List<Move> gameMoves = new ArrayList<>();

    @After
    public void tearDown() {
        gameMoves.clear();
    }

    // TODO parametrize (moves history, current move, validation result)
    /**
     * Play Fool's Mate.
     *
     * Ref.:
     *     https://www.chess.com/article/view/the-fastest-possible-checkmate-in-chess
     */
    @Test
    public void testIsCheckMateFoolMate() {
        // Prerequisites
        gameMoves.add(new Move(new Pawn(WHITE), new Space(F, TWO), new Space(F, THREE), firstPlayer));
        gameMoves.add(new Move(new Pawn(BLACK), new Space(E, SEVEN), new Space(E, FIVE), secondPlayer));
        gameMoves.add(new Move(new Pawn(WHITE), new Space(G, TWO), new Space(G, FOUR), firstPlayer));
        Chessboard chessboard = new StandardChessboardBuilder(gameMoves, game).build();
        // Expected move for checkmate
        Move currentMove = new Move(new Queen(BLACK), new Space(D, EIGHT), new Space(H, FOUR), secondPlayer);
        currentMove.setGame(game);
        assertTrue(chessboard.isCheckMate(currentMove));
    }

    /**
     * Play Fool's Mate - Variation.
     *
     * Ref.:
     *     https://www.chess.com/article/view/the-fastest-possible-checkmate-in-chess
     */
    @Test
    public void testIsCheckMateFoolMateVariation() {
        // Prerequisites
        gameMoves.add(new Move(new Pawn(WHITE), new Space(D, ONE), new Space(D, FOUR), firstPlayer));
        gameMoves.add(new Move(new Pawn(BLACK), new Space(F, SEVEN), new Space(F, FIVE), secondPlayer));
        gameMoves.add(new Move(new Bishop(WHITE), new Space(A, THREE), new Space(G, FIVE), firstPlayer));
        gameMoves.add(new Move(new Pawn(BLACK), new Space(H, SEVEN), new Space(H, SIX), secondPlayer));
        gameMoves.add(new Move(new Bishop(WHITE), new Space(G, FIVE), new Space(F, FOUR), firstPlayer));
        gameMoves.add(new Move(new Pawn(BLACK), new Space(G, SEVEN), new Space(G, FIVE), secondPlayer));
        gameMoves.add(new Move(new Bishop(WHITE), new Space(F, FOUR), new Space(G, THREE), firstPlayer));
        gameMoves.add(new Move(new Pawn(BLACK), new Space(F, FIVE), new Space(F, FOUR), secondPlayer));
        gameMoves.add(new Move(new Pawn(WHITE), new Space(E, TWO), new Space(E, THREE), firstPlayer));
        gameMoves.add(new Move(new Pawn(BLACK), new Space(H, SIX), new Space(H, FIVE), secondPlayer));
        gameMoves.add(new Move(new Bishop(WHITE), new Space(F, ONE), new Space(D, THREE), firstPlayer));
        gameMoves.add(new Move(new Rook(BLACK), new Space(H, EIGHT), new Space(H, SIX), secondPlayer));
        gameMoves.add(new Move(new Queen(WHITE), new Space(D, ONE), new Space(H, FIVE), firstPlayer));
        gameMoves.add(new Move(new Rook(BLACK), new Space(H, SIX), new Space(H, FIVE), secondPlayer));

        Chessboard chessboard = new StandardChessboardBuilder(gameMoves, game).build();
        // Expected move for checkmate
        Move currentMove = new Move(new Bishop(WHITE), new Space(D, THREE), new Space(G, SIX), firstPlayer);
        currentMove.setGame(game);
        assertTrue(chessboard.isCheckMate(currentMove));
    }
}
