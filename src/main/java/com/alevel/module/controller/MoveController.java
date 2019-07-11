package com.alevel.module.controller;

import com.alevel.module.controller.exceptions.InvalidMoveException;
import com.alevel.module.controller.utils.Response;
import com.alevel.module.model.chessboard.Move;
import com.alevel.module.model.game.Game;
import com.alevel.module.model.game.Player;
import com.alevel.module.service.GameOperations;
import com.alevel.module.service.MoveOperations;
import com.alevel.module.service.PlayerOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

// TODO get rid of wildcard imports everywhere

@RestController
@RequestMapping("/chess/move")
public class MoveController {

    private MoveOperations moveOperations;
    private GameOperations gameOperations;
    private PlayerOperations playerOperations;

    @Autowired
    public MoveController(MoveOperations moveOperations,
                          GameOperations gameOperations,
                          PlayerOperations playerOperations) {
        this.moveOperations = moveOperations;
        this.gameOperations = gameOperations;
        this.playerOperations = playerOperations;
    }

    // TODO response: statuses, custom codes and messages based on validation results (IllegalArgumentException?)
    @PostMapping("/create")
    public ResponseEntity save(@RequestBody Move move) {
        try {
            // TODO check piece color by player (if first player in a game, it's white; if second, then black);
            //  color can also be taken from request
            // TODO ensure a move's player is the same as a current user (i.e. authenticated user)
            Optional<Player> playerOptional = playerOperations.find(move.getPlayer().getId());
            // TODO get the game the user is currently playing (if any; if none, raise GameNotFoundException)
            Optional<Game> gameOptional = gameOperations.find(move.getGame().getId());
            System.out.println(" move: -------" + move);
            Long id = moveOperations.save(move);
            if (id == null) { // TODO handle checkmate case better (null is not obvious)
                // TODO end the game
                return new ResponseEntity<>(
                        new Response("Checkmate! A " + move.getPiece().getColor() + " side won."),
                        HttpStatus.CREATED);
            } else {
                move.setId(id);
                return new ResponseEntity<>(move, HttpStatus.CREATED);
            }
        } catch (IllegalArgumentException e) {
            // TODO catch it
            // e.g. "Please provide a correct Rank value"
            return new ResponseEntity<>(new Response("Illegal argument. " + e.getMessage()), HttpStatus.FORBIDDEN);
        } catch (InvalidMoveException e) {
            return new ResponseEntity<>(new Response("The move is invalid."), HttpStatus.FORBIDDEN);
        }
    }

}
