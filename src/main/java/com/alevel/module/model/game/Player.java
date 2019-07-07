package com.alevel.module.model.game;

import com.alevel.module.model.chessboard.Move;
import com.alevel.module.model.chessboard.Space;
import com.alevel.module.model.piece.Piece;
import com.alevel.module.model.piece.configs.Color;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@Entity
@Table(name="players")
public class Player {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;

    @OneToMany(mappedBy = "player")
    private List<Move> moves = new ArrayList<>();

    @OneToMany(mappedBy = "firstPlayer")
    private List<Game> firstPlayerGames = new ArrayList<>();

    @OneToMany(mappedBy = "secondPlayer")
    private List<Game> secondPlayerGames = new ArrayList<>();

    @Transient
    private Color piecesColor;

    public Player(String username) {
        this.username = username;
    }

    public Player(Long id) {
        this.id = id;
    }

    public Player() {
    }

    @JsonGetter("username")
    public String getUsername() {
        return username;
    }

    @JsonSetter("username")
    public void setUsername(String username) {
        this.username = username;
    }

    public boolean makeMove(Piece piece, Space space) {
        // TODO validate (validation logic to be placed in the `Move` class
        // TODO isCheckmate
        return true;
    }

    public Color getPiecesColor() {
        return piecesColor;
    }

    public void setPiecesColor(Color piecesColor) {
        this.piecesColor = piecesColor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(username, player.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public List<Game> getFirstPlayerGames() {
        return firstPlayerGames;
    }

    public void setFirstPlayerGames(List<Game> firstPlayerGames) {
        this.firstPlayerGames = firstPlayerGames;
    }

    public List<Game> getSecondPlayerGames() {
        return secondPlayerGames;
    }

    public void setSecondPlayerGames(List<Game> secondPlayerGames) {
        this.secondPlayerGames = secondPlayerGames;
    }
}
