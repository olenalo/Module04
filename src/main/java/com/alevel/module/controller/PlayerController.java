package com.alevel.module.controller;

import com.alevel.module.auth.PlayerDetailsService;
import com.alevel.module.model.game.Player;
import com.alevel.module.service.PlayerOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/chess/player")
public class PlayerController {

    private PlayerOperations playerOperations;
    private PlayerDetailsService playerDetailsService;

    @Autowired
    public PlayerController(PlayerOperations playerOperations, PlayerDetailsService playerDetailsService) {
        this.playerOperations = playerOperations;
        // TODO not sure if it should be wired in controller; but where?
        this.playerDetailsService = playerDetailsService;
    }

    // TODO fetch by tokens
    Authentication getAuthentication(String username) { // (String token) {
        UserDetails userDetails = this.playerDetailsService.loadUserByUsername(username);// getUsername(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
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
    public boolean login(@RequestBody Player player) {
        // TODO check password
        return authenticate(player);
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    // TODO validate required params
    public Player register(@RequestBody Player player) {
        // TODO store password hash, add salting
        // TODO handle 500 (e.g. unique constraints violation)
        Long id = playerOperations.save(player);
        player.setId(id);
        authenticate(player);
        return player;
    }
}
