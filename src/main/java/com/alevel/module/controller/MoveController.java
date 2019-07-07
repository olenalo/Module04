package com.alevel.module.controller;

import com.alevel.module.model.chessboard.Move;
import com.alevel.module.model.game.Game;
import com.alevel.module.model.game.Player;
import com.alevel.module.service.GameOperations;
import com.alevel.module.service.MoveOperations;
import com.alevel.module.service.PlayerOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
        // TODO research (1) Hibernate specific and (2) Spring Data JPA ways to fetch associated entries
        //  ref.: https://stackoverflow.com/a/31699855
        this.gameOperations = gameOperations;
        this.playerOperations = playerOperations;
    }

    // TODO response: statuses, custom codes and messages based on validation results (IllegalArgumentException?)
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Move save(@RequestBody Move move) {

        // TODO get a current user (player) from the move (and do what? -> TODO: save attempts? additional checks?)
        // TODO handle not found case (use PlayerFoundException)
        Optional<Player> player = playerOperations.find(move.getPlayer().getId());
        System.out.println("Player: " + player);

        // TODO get the game the user is currently playing (if any; if none, what then?)
        Optional<Game> game = gameOperations.find(move.getGame().getId());
        System.out.println("Game: " + player);

        // TODO handle not found case (use GameFoundException)
        Long id = moveOperations.save(move);
        move.setId(id);
        return move; // FIXME  piece's "type": null
    }
}
