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

    @PostMapping("/login")
    public boolean login(HttpServletRequest request,
                         @RequestBody Player player) {
        // TODO check optional
        // TODO check password
        // Authentication currentAuthentication = SecurityContextHolder.getContext().getAuthentication();
        // UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(player.getUsername(), player.getPassword());
        //System.out.println("authenticationToken: " + authenticationToken);

        Authentication auth = this.getAuthentication(player.getUsername());  // new UsernamePasswordAuthenticationToken(player.getUsername(), player.getPassword());
        System.out.println("auth: " + auth);

        if (auth != null) {  // !auth.isAuthenticated()
            // Authentication auth = new UsernamePasswordAuthenticationToken(player.getUsername(), player.getPassword());
            SecurityContextHolder.getContext().setAuthentication(auth);
        } else {
            return false;
        }
        System.out.println("user roles: " + SecurityContextHolder.getContext().getAuthentication().getAuthorities());
        System.out.println("username: " + SecurityContextHolder.getContext().getAuthentication().getName());
        System.out.println("getPrincipal: " + SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return true;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    // TODO validate required params
    // TODO set a "USER" role
    public Player register(@RequestBody Player player) {
        // TODO store password hash, add salting
        // TODO handle 500 (e.g. unique constraints violation)
        Long id = playerOperations.save(player);
        player.setId(id);
        // TODO authenticate
        return player;
    }
}
