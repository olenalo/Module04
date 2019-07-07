package com.alevel.module.controller;

import com.alevel.module.model.game.Player;
import com.alevel.module.service.PlayerOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chess/player")
public class PlayerController {

    private PlayerOperations playerOperations;

    @Autowired
    public PlayerController(PlayerOperations playerOperations) {
        this.playerOperations = playerOperations;
    }

    // TODO @GetMapping("/login")

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    // TODO validate required params
    // TODO set a "USER" role
    public Player register(@RequestBody Player player) {
        // TODO store password hash, add salting
        Long id = playerOperations.save(player);
        player.setId(id);
        return player;
    }
}
