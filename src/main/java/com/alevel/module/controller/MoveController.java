package com.alevel.module.controller;

import com.alevel.module.model.chessboard.Move;
import com.alevel.module.service.MoveOperations;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chess/move")
public class MoveController {

    private MoveOperations moveOperations;

    public MoveController(MoveOperations moveOperations) {
        this.moveOperations = moveOperations;
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Move save(@RequestBody Move move) {
        Long id = moveOperations.save(move);
        move.setId(id);
        // TODO
        return move;
    }
}
