package com.alevel.module.controller;

import com.alevel.module.controller.exceptions.InvalidMoveException;
import com.alevel.module.model.chessboard.Move;
import com.alevel.module.model.game.Game;
import com.alevel.module.model.game.Player;
import com.alevel.module.service.GameOperations;
import com.alevel.module.service.MoveOperations;
import com.alevel.module.service.PlayerOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

// TODO get rid of wildcard imports everywhere

@RestController
@RequestMapping("/chess/move")
public class MoveController {

    // TODO consider coarse-grained facade vs fine-grained services, ref. https://stackoverflow.com/a/29394487
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
        // TODO catch 'IllegalArgumentException' e.g. Please provide a correct Rank value
        try {
            // TODO check piece color by player (if first player in a game, it's white; if second, then black)
            // TODO consider getting a player from the current user (i.e. from the auth pipeline)
            Optional<Player> playerOptional = playerOperations.find(move.getPlayer().getId());
            // TODO get the game the user is currently playing (if any; if none, raise GameNotFoundException)
            Optional<Game> gameOptional = gameOperations.find(move.getGame().getId());
            Long id = moveOperations.save(move);
            move.setId(id);
            // FIXME  piece's "type": null
            return new ResponseEntity(move, HttpStatus.CREATED);
        } catch (InvalidMoveException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.FORBIDDEN);
        }
    }
}
