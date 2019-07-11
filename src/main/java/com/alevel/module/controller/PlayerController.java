package com.alevel.module.controller;

import com.alevel.module.auth.PlayerDetailsService;
import com.alevel.module.controller.utils.Response;
import com.alevel.module.model.game.Player;
import com.alevel.module.model.game.PlayerDto;
import com.alevel.module.service.PlayerOperations;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static com.alevel.module.auth.utils.PasswordUtils.validatePlayer;

@RestController
@RequestMapping("/chess/player")
public class PlayerController {

    private PlayerOperations playerOperations;
    private PlayerDetailsService playerDetailsService;
    private ModelMapper modelMapper;

    @Autowired
    public PlayerController(PlayerOperations playerOperations,
                            PlayerDetailsService playerDetailsService,
                            ModelMapper modelMapper) {
        this.playerOperations = playerOperations;
        this.playerDetailsService = playerDetailsService;
        this.modelMapper = modelMapper;
    }

    // TODO fetch by tokens
    Authentication getAuthentication(String username) { // String token
        UserDetails userDetails = this.playerDetailsService.loadUserByUsername(username); // token
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    private PlayerDto convertToDto(Player player) {
        return modelMapper.map(player, PlayerDto.class);
    }

    private Player convertToEntity(PlayerDto playerDto) {
        return modelMapper.map(playerDto, Player.class);
    }

    private boolean authenticate(Player player) {
        Authentication auth = this.getAuthentication(player.getUsername());
        // System.out.println("auth: " + auth);
        if (auth != null) {
            SecurityContextHolder.getContext().setAuthentication(auth);
            // System.out.println("user roles: " + SecurityContextHolder.getContext().getAuthentication().getAuthorities());
            // System.out.println("username: " + SecurityContextHolder.getContext().getAuthentication().getName());
            // System.out.println("getPrincipal: " + SecurityContextHolder.getContext().getAuthentication().getPrincipal());
            return true;
        } else {
            return false;
        }
    }

    @PostMapping("/register")
    // TODO validate required params
    public ResponseEntity register(@RequestBody PlayerDto playerDto) {
        System.out.println("Input playerDto: " + playerDto);
        try {
            Player player = convertToEntity(playerDto);
            Long id = playerOperations.save(player);
            player.setId(id);
            authenticate(player);
            return new ResponseEntity<>(playerDto, HttpStatus.CREATED);
        } catch (Exception e) {
            // TODO handle various causes of 500 (e.g. unique constraints violation);
            //  check that account doesn't already exist
            return new ResponseEntity<>(new Response("Cannot register a user."), HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody PlayerDto playerDto) {
        Player dbPlayer = null;
        Optional<Player> playerOptional = playerOperations.find(playerDto.getUsername());
        if(playerOptional.isPresent()) {
            dbPlayer = playerOptional.get();
        }
        if (dbPlayer != null) {
            if (validatePlayer(playerDto, dbPlayer)) {
                if (authenticate(convertToEntity(playerDto))) {
                    return new ResponseEntity<>(playerDto, HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(new Response("Could not authenticate."), HttpStatus.UNAUTHORIZED);
                }
            } else {
                return new ResponseEntity<>(new Response("Incorrect credentials."), HttpStatus.UNAUTHORIZED);
            }
        } else {
            return new ResponseEntity<>(new Response("User does not exist."), HttpStatus.UNAUTHORIZED);
        }
    }
}
