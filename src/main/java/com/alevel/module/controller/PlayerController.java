package com.alevel.module.controller;

import com.alevel.module.auth.PlayerDetailsService;
import com.alevel.module.model.game.Player;
import com.alevel.module.model.game.PlayerDto;
import com.alevel.module.service.PlayerOperations;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/login")
    public boolean login(@RequestBody PlayerDto playerDto) {
        // TODO check password
        return authenticate(convertToEntity(playerDto));
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    // TODO validate required params
    public PlayerDto register(@RequestBody PlayerDto playerDto) {
        // TODO store password hash
        // TODO add password salting
        // TODO handle 500 (e.g. unique constraints violation)
        Player player = convertToEntity(playerDto);
        Long id = playerOperations.save(player);
        player.setId(id);
        authenticate(player);
        return playerDto;
    }
}
