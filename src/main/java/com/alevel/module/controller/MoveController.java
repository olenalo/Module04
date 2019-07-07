package com.alevel.module.controller;

import com.alevel.module.controller.exceptions.InvalidMoveException;
import com.alevel.module.model.chessboard.Move;
import com.alevel.module.model.game.Game;
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
        // TODO research (1) Hibernate specific and (2) Spring Data JPA ways to fetch associated entries
        //  ref.: https://stackoverflow.com/a/31699855
        this.gameOperations = gameOperations;
        this.playerOperations = playerOperations;
    }

    // TODO response: statuses, custom codes and messages based on validation results (IllegalArgumentException?)
    @PostMapping("/create")
    public ResponseEntity save(HttpServletRequest request, @RequestBody Move move) {  // @RequestBody Move move
        //authentication.getName();
        //UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        //System.out.println("User has authorities: " + userDetails.getAuthorities());
        Authentication currentAuthentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("-------isAuthenticated: --------" + currentAuthentication.isAuthenticated());

        // if (currentAuthentication.isAuthenticated()){
        // TODO catch 'IllegalArgumentException' e.g. Please provide a correct Rank value
        try {
            // System.out.println("userTest roles: " + currentAuthentication.getAuthorities());
            // TODO get the game the user is currently playing (if any; if none, raise GameNotFoundException)
            Optional<Game> game = gameOperations.find(move.getGame().getId());
            System.out.println("Game: " + game);
            Long id = moveOperations.save(move);
            move.setId(id);
            return new ResponseEntity(move, HttpStatus.CREATED); // FIXME  piece's "type": null
        } catch (InvalidMoveException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.FORBIDDEN);
        }

    }
}
