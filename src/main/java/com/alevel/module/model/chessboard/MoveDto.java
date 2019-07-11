package com.alevel.module.model.chessboard;


import com.alevel.module.model.game.Game;
import com.alevel.module.model.game.Player;
import com.alevel.module.model.piece.Piece;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import javax.persistence.*;


@JsonDeserialize(as=MoveDto.class)
public class MoveDto {
    @JsonProperty("id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("game")
    private Game game;

    @JsonProperty("player")
    private Player player;

    @JsonProperty("piece")
    private Piece piece;

    @JsonProperty("currentSpace")
    private Space currentSpace;

    @JsonProperty("destinationSpace")
    private Space destinationSpace;

    // Ref.: https://stackoverflow.com/a/51014378
    public MoveDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public Space getCurrentSpace() {
        return currentSpace;
    }

    public void setCurrentSpace(Space currentSpace) {
        this.currentSpace = currentSpace;
    }

    public Space getDestinationSpace() {
        return destinationSpace;
    }

    public void setDestinationSpace(Space destinationSpace) {
        this.destinationSpace = destinationSpace;
    }

    @Override
    public String toString() {
        return "MoveDto{" +
                "piece=" + piece +
                ", currentSpace=" + currentSpace +
                ", destinationSpace=" + destinationSpace +
                '}';
    }
}
