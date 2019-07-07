package com.alevel.module.controller;

import com.alevel.module.model.game.Game;
import com.alevel.module.service.GameOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    @ResponseStatus(HttpStatus.CREATED)
    public Game save(@RequestBody Game game) {
        Long id = gameOperations.save(game);
        game.setId(id);
        return game;
    }
}
