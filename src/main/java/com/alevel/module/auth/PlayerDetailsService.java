package com.alevel.module.auth;

import com.alevel.module.model.game.Player;
import com.alevel.module.service.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class PlayerDetailsService implements UserDetailsService {

    private PlayerRepository playerRepository;

    @Autowired
    public PlayerDetailsService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Player> playerOptional = playerRepository.findByUsername(username);
        final Player player;
        if(playerOptional.isPresent()) {
            player = playerOptional.get();
            return new PlayerUserPrincipal(player);
        } else {
            throw new UsernameNotFoundException(username);
        }
    }

}
