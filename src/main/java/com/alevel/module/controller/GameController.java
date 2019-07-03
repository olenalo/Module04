package com.alevel.module.controller;

import com.alevel.module.persistence.chessboard.Chessboard;
import com.alevel.module.persistence.chessboard.Space;
import com.alevel.module.persistence.chessboard.Square;
import com.alevel.module.persistence.chessboard.configs.File;
import com.alevel.module.persistence.chessboard.configs.Rank;
import com.alevel.module.persistence.game.Game;
import com.alevel.module.persistence.game.Lobby;
import com.alevel.module.persistence.game.Player;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/chess")
public class GameController {

    @PostMapping("/demo")
    public @ResponseBody Game demoGame(@RequestBody String username) {
        Player firstPlayer = new Player(username);
        Lobby lobby = new Lobby(firstPlayer);
        Player secondPlayer = new Player("yoyo");
        List<Square> squares = new ArrayList<>();

        squares.add(new Square(new Space(File.A, Rank.ONE)));
        squares.add(new Square(new Space(File.B, Rank.ONE)));
        squares.add(new Square(new Space(File.C, Rank.ONE)));
        squares.add(new Square(new Space(File.D, Rank.ONE)));
        squares.add(new Square(new Space(File.E, Rank.ONE)));
        squares.add(new Square(new Space(File.F, Rank.ONE)));
        squares.add(new Square(new Space(File.G, Rank.ONE)));
        squares.add(new Square(new Space(File.H, Rank.ONE)));

        squares.add(new Square(new Space(File.A, Rank.TWO)));
        squares.add(new Square(new Space(File.B, Rank.TWO)));
        squares.add(new Square(new Space(File.C, Rank.TWO)));
        squares.add(new Square(new Space(File.D, Rank.TWO)));
        squares.add(new Square(new Space(File.E, Rank.TWO)));
        squares.add(new Square(new Space(File.F, Rank.TWO)));
        squares.add(new Square(new Space(File.G, Rank.TWO)));
        squares.add(new Square(new Space(File.H, Rank.TWO)));

        Chessboard chessboard = new Chessboard(squares);
        return new Game(firstPlayer, secondPlayer, chessboard);
    }

    @PostMapping("/join")
    // TODO 301 if created, 200 if joined; @ResponseStatus(HttpStatus.CREATED)
    public Lobby joinLobby(@RequestBody Player player) { // TODO add lobby param
        Lobby lobby = new Lobby(player);
        // TODO save to db
        return lobby;
    }

    // TODO makeMove


}
