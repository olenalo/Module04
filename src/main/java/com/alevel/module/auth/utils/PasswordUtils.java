package com.alevel.module.auth.utils;

import com.alevel.module.model.game.Player;
import com.alevel.module.model.game.PlayerDto;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtils {

    /**
     * Validate a player data.
     *
     * Check if a provided player data matches respective stored identity.
     * Check against username and decoded password.
     *
     * @param playerDto player data from client.
     * @param player player data from database.
     * @return validation results (true if all data matched).
     */
    public static boolean validatePlayer(PlayerDto playerDto, Player player) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return playerDto.getUsername().equalsIgnoreCase(player.getUsername()) &&
                encoder.matches(playerDto.getPassword(), player.getPassword());
    }

    /**
     * Encode a password for it to be securely stored.
     *
     * TODO add password salting (update here and in the decoding flow).
     *
     * @param password password to encode.
     * @return encoded password.
     */
    public static String encodePassword(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }
}
