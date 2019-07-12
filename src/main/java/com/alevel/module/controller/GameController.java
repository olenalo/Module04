package com.alevel.module.controller;

import com.alevel.module.model.game.Game;
import com.alevel.module.service.operation.GameOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chess/game")
public class GameController {

    private GameOperations gameOperations;

    @Autowired
    public GameController(GameOperations gameOperations) {
        this.gameOperations = gameOperations;
    }

    @PostMapping("/create")
    public ResponseEntity save(@RequestBody Game game) {
        System.out.println("Input game: " + game);
        Long id = gameOperations.save(game);
        game.setId(id);
        // TODO handle erroneous cases (think of any)
        return new ResponseEntity<>(game, HttpStatus.CREATED);
    }
}
