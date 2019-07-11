package com.alevel.module.controller;

import com.alevel.module.controller.exceptions.InvalidMoveException;
import com.alevel.module.controller.utils.Response;
import com.alevel.module.model.chessboard.Move;
import com.alevel.module.model.chessboard.MoveDto;
import com.alevel.module.model.game.Game;
import com.alevel.module.model.game.Player;
import com.alevel.module.service.GameOperations;
import com.alevel.module.service.MoveOperations;
import com.alevel.module.service.PlayerOperations;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Optional;

// TODO get rid of wildcard imports everywhere

@RestController
@RequestMapping("/chess/move")
public class MoveController {

    private MoveOperations moveOperations;
    private GameOperations gameOperations;
    private PlayerOperations playerOperations;
    private ModelMapper modelMapper;

    @Autowired
    public MoveController(MoveOperations moveOperations,
                          GameOperations gameOperations,
                          PlayerOperations playerOperations,
                          ModelMapper modelMapper) {
        this.moveOperations = moveOperations;
        this.gameOperations = gameOperations;
        this.playerOperations = playerOperations;
        this.modelMapper  =  modelMapper;
    }

    private MoveDto convertToDto(Move move) {
        return modelMapper.map(move, MoveDto.class);
    }

    private Move convertToEntity(MoveDto moveDto) {
        return modelMapper.map(moveDto, Move.class);
    }

    /**
     * Save a move.
     *
     * Only valid move will be saved to the db.
     */
    @PostMapping("/create")
    public ResponseEntity save(@RequestBody MoveDto moveDto) {
        try {
            // TODO check piece color by player (if first player in a game, it's white; if second, then black);
            //  color can also be taken from request
            // TODO ensure a move's player is the same as a current user (i.e. authenticated user)
            Optional<Player> playerOptional = playerOperations.find(moveDto.getPlayer().getId());
            // TODO get the game the user is currently playing (if any; if none, raise GameNotFoundException)
            Optional<Game> gameOptional = gameOperations.find(moveDto.getGame().getId());
            System.out.println("Current move: -------" + moveDto);
            Long id = moveOperations.save(convertToEntity(moveDto));
            if (id == null) { // TODO handle checkmate case better (null is not obvious)
                // TODO end the game
                return new ResponseEntity<>(
                        new Response("Checkmate! A " + moveDto.getPiece().getColor() + " side won."),
                        HttpStatus.CREATED);
            } else {
                moveDto.setId(id);
                return new ResponseEntity<>(moveDto, HttpStatus.CREATED);
            }
        } catch (IllegalArgumentException e) {
            // FIXME catch it e.g. "Please provide a correct Rank value"
            return new ResponseEntity<>(new Response("Illegal argument. " + e.getMessage()), HttpStatus.FORBIDDEN);
        } catch (InvalidMoveException e) {
            return new ResponseEntity<>(new Response("The move is invalid."), HttpStatus.FORBIDDEN);
        }
    }

}
