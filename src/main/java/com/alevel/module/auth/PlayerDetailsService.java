package com.alevel.module.auth;

import com.alevel.module.auth.configs.UserRoles;
import com.alevel.module.model.game.Player;
import com.alevel.module.model.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.alevel.module.auth.configs.UserRoles.ROLE_USER;

@Service
public class PlayerDetailsService implements UserDetailsService {

    private PlayerRepository playerRepository;

    @Autowired
    public PlayerDetailsService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Player> player = playerRepository.findByUsername(username);
        // TODO refactor
        String email = player.map(Player::getEmail).orElseThrow(
                () -> new UsernameNotFoundException("No user found with username: "+ username));
        String password = player.map(Player::getPassword).orElseThrow(
                () -> new UsernameNotFoundException("No user found with username: "+ username));

        // TODO fetch roles dynamically
        /**
        List<String> roles = player.map(Player::getRoles).orElseThrow(
                () -> new UsernameNotFoundException("No user found with username: "+ username));
        **/
        List<String> roles = new ArrayList<>();
        roles.add(ROLE_USER.getShortTitle());

        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;
        return  new org.springframework.security.core.userdetails.User(
                email,
                password.toLowerCase(),
                enabled,
                accountNonExpired,
                credentialsNonExpired,
                accountNonLocked,
                getAuthorities(roles));
    }

    private static List<GrantedAuthority> getAuthorities (List<String> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String role : roles) {
            authorities.add(new SimpleGrantedAuthority(role));
        }
        return authorities;
    }

}
