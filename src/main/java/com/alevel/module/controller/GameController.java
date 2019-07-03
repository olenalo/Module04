package com.alevel.module.controller;

import com.alevel.module.persistence.chessboard.Chessboard;
import com.alevel.module.persistence.chessboard.Move;
import com.alevel.module.persistence.chessboard.Space;
import com.alevel.module.persistence.chessboard.configs.Rank;
import com.alevel.module.persistence.game.Game;
import com.alevel.module.persistence.game.Lobby;
import com.alevel.module.persistence.game.Player;
import com.alevel.module.persistence.piece.Piece;
import com.alevel.module.persistence.piece.pieces.King;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static com.alevel.module.controller.utils.GameControllerUtils.createDummyChessboard;
import static com.alevel.module.persistence.chessboard.configs.File.A;
import static com.alevel.module.persistence.chessboard.configs.File.B;
import static com.alevel.module.persistence.chessboard.configs.Rank.ONE;
import static com.alevel.module.persistence.chessboard.configs.Rank.TWO;
import static com.alevel.module.persistence.chessboard.configs.Rank.FOUR;
import static com.alevel.module.persistence.piece.configs.Color.WHITE;

@RestController
@RequestMapping("/chess")
public class GameController {

    @PostMapping("/demoGame")
    public @ResponseBody Game demoGame(@RequestBody String username) {
        Player firstPlayer = new Player(username);
        Lobby lobby = new Lobby(firstPlayer);
        Player secondPlayer = new Player("yoyo");
        return new Game(firstPlayer, secondPlayer, createDummyChessboard());
    }

    @PostMapping("/join")
    // TODO 301 if created, 200 if joined; @ResponseStatus(HttpStatus.CREATED)
    public Lobby joinLobby(@RequestBody Player player) { // TODO add lobby param
        Lobby lobby = new Lobby(player);
        // TODO save to db
        return lobby;
    }

    @PostMapping("/demoMove")
    public boolean demoMove() {
        Piece piece = new King(WHITE);
        // Example of valid movement
        Space currentSpace = new Space(A, ONE);
        Space destinationSpace = new Space(B, TWO);
        // Example of invalid movement
        /*
        Space currentSpace = new Space(A, ONE);
        Space destinationSpace = new Space(B, FOUR);
        */
        Move move = new Move(piece, currentSpace, destinationSpace);
        return move.getPiece().doMove(move, createDummyChessboard());
    }

    @PostMapping("/makeMove")
    // TODO response: statuses, custom codes and messages based on validation results (IllegalArgumentException?)
    public boolean makeMove(@RequestBody Move move) {
        // TODO get current user (player) from request
        // TODO get an open game the user is currently playing
        // TODO get the game's chessboard
        return move.getPiece().doMove(move, createDummyChessboard());
    }

}
