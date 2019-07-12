package com.alevel.module.controller;

import com.alevel.module.controller.exceptions.GameNotFoundException;
import com.alevel.module.controller.exceptions.InvalidMoveException;
import com.alevel.module.controller.utils.Response;
import com.alevel.module.model.chessboard.Move;
import com.alevel.module.model.chessboard.MoveDto;
import com.alevel.module.model.game.Player;
import com.alevel.module.service.operation.MoveOperations;
import com.alevel.module.service.operation.PlayerOperations;
import org.modelmapper.ModelMapper;
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
    private PlayerOperations playerOperations;
    private ModelMapper modelMapper;

    @Autowired
    public MoveController(MoveOperations moveOperations,
                          PlayerOperations playerOperations,
                          ModelMapper modelMapper) {
        this.moveOperations = moveOperations;
        this.playerOperations = playerOperations;
        this.modelMapper  =  modelMapper;
    }

    private Move convertToEntity(MoveDto moveDto) {
        Move move = modelMapper.map(moveDto, Move.class);
        move.setPieceTitle(moveDto.getPiece().getType().getShortTitle());
        return move;
    }

    /**
     * Save a move.
     *
     * Only valid move will be saved to the db.
     */
    @PostMapping("/create")
    public ResponseEntity save(@RequestBody MoveDto moveDto) {
        try {
            System.out.println("Current move: -------" + moveDto);
            // TODO ensure a move's player is the same as a current user (i.e. authenticated user)
            Optional<Player> playerOptional = playerOperations.find(moveDto.getPlayer().getId());
            Long id = moveOperations.save(convertToEntity(moveDto));
            // TODO Cache the updated states
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
        } catch (GameNotFoundException e) {
            return new ResponseEntity<>(new Response("Provided game id is invalid."), HttpStatus.FORBIDDEN);
        }
    }

}
