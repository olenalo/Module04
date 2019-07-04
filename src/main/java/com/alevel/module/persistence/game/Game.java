package com.alevel.module.persistence.game;

import com.alevel.module.persistence.chessboard.Chessboard;
import com.alevel.module.persistence.chessboard.Move;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="games")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn()
    private Player firstPlayer;

    @ManyToOne
    @JoinColumn()
    private Player secondPlayer;

    @OneToMany(mappedBy = "game")
    private List<Move> moves = new ArrayList<>();

    @Transient
    private Chessboard chessboard;
    // TODO results (captured pieces)
    // TODO startTimestamp
    // TODO finishTimestamp

    // TODO keep track of pieces removed from the board
    // TODO record the moves

    public Game(Player firstPlayer, Player secondPlayer, Chessboard chessboard) {
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
        this.chessboard = chessboard;
    }

    public Game() {
    }

    public Player getFirstPlayer() {
        return firstPlayer;
    }

    public Player getSecondPlayer() {
        return secondPlayer;
    }

    public Chessboard getChessboard() {
        return chessboard;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFirstPlayer(Player firstPlayer) {
        this.firstPlayer = firstPlayer;
    }

    public void setSecondPlayer(Player secondPlayer) {
        this.secondPlayer = secondPlayer;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }
}
