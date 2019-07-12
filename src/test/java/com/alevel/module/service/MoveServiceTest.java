package com.alevel.module.service;

import com.alevel.module.ModuleApplication;
import com.alevel.module.model.chessboard.Move;
import com.alevel.module.model.chessboard.Space;
import com.alevel.module.model.game.Game;
import com.alevel.module.model.piece.pieces.Pawn;
import com.alevel.module.model.piece.pieces.Queen;
import com.alevel.module.service.repository.GameRepository;
import com.alevel.module.service.repository.MoveRepository;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.alevel.module.model.chessboard.configs.File.*;
import static com.alevel.module.model.chessboard.configs.Rank.*;
import static com.alevel.module.model.piece.configs.Color.BLACK;
import static com.alevel.module.model.piece.configs.Color.WHITE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = ModuleApplication.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)  // Ref.: https://stackoverflow.com/a/43896841
public class MoveServiceTest {

    @Autowired
    private MoveService moveService;
    @Autowired
    private MoveRepository moveRepository;
    @Autowired
    private GameRepository gameRepository;
    /*
    @Autowired
    public MoveServiceTest(MoveService moveService, MoveRepository moveRepository, GameRepository gameRepository) {
        this.moveService = moveService;
        this.moveRepository = moveRepository;
        this.gameRepository = gameRepository;
    }
     */

    /**
     * Play Fool's Mate.
     *
     * Ref.:
     *     https://www.chess.com/article/view/the-fastest-possible-checkmate-in-chess
     */
    @Test
    @Ignore("Skip till figure out an issue (with mocking).")
    public void testSaveFoolMate() {
        // Test set up
        List<Move> gameMoves = new ArrayList<>();
        gameMoves.add(new Move(new Pawn(WHITE), new Space(F, TWO), new Space(F, THREE)));
        gameMoves.add(new Move(new Pawn(BLACK), new Space(E, SEVEN), new Space(E, FIVE)));
        gameMoves.add(new Move(new Pawn(WHITE), new Space(G, TWO), new Space(G, FOUR)));

        Game game = new Game(1L);
        Optional<Game> gameOptional = Optional.of(game);
        // Expected move for checkmate
        Move currentMove = new Move(new Queen(BLACK), new Space(D, EIGHT), new Space(H, FOUR));
        currentMove.setGame(game);
        currentMove.setId(1L);

        // FIXME
        // Ref. https://www.baeldung.com/injecting-mocks-in-spring
        // Mockito.when(moveService.findAll(currentMove.getGame())).thenReturn(gameMoves);
        Mockito.when(gameRepository.findById(currentMove.getGame().getId())).thenReturn(gameOptional);
        Mockito.when(moveRepository.save(currentMove)).thenReturn(currentMove);

        // Test the logic
        Long resultId = moveService.save(currentMove);
        // assertEquals(currentMove.getId(), resultId);
        assertNull(resultId); // Checkmate!

    }

}
