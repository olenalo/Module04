package com.alevel.module.controller;

import com.alevel.module.model.game.Player;
import com.alevel.module.service.PlayerOperations;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chess/player")
public class PlayerController {

    private PlayerOperations playerOperations;

    public PlayerController(PlayerOperations playerOperations) {
        this.playerOperations = playerOperations;
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Player save(@RequestBody Player player) {
        Long id = playerOperations.save(player);
        player.setId(id);
        return player;
    }
}
