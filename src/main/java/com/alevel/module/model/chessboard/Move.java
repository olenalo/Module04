package com.alevel.module.model.chessboard;

import com.alevel.module.model.game.Game;
import com.alevel.module.model.game.Player;
import com.alevel.module.model.piece.Piece;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import javax.persistence.*;

/**
 * TODO add docstring
 *
 * Only valid move will be saved to the db.
 */
@JsonIgnoreProperties({"pieceTitle", "spaceFile", "spaceRank", "currentSpace"})
@JsonDeserialize(as=Move.class)
@Entity
@Table(name="moves")
public class Move {
    @JsonProperty("id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("game")
    @ManyToOne
    @JoinColumn()
    private Game game;

    @JsonProperty("player")
    @ManyToOne
    @JoinColumn()
    private Player player;

    @JsonProperty("piece")
    @Transient
    private Piece piece;

    @Transient
    private Space currentSpace;

    @JsonProperty("destinationSpace")
    @Transient
    private Space destinationSpace;

    // TODO disallow null values whenever needed, throughout the project (JPA)
    @Column(name = "pieceTitle")
    private String pieceTitle;

    @Column(name = "spaceFile")
    private String spaceFile;

    @Column(name = "spaceRank")
    private String spaceRank;

    // TODO timestamp

    // TODO consider getting rid of this constructor (a move can't exist without a game)
    public Move(Piece piece, Space currentSpace, Space destinationSpace) {
        this.piece = piece;
        this.currentSpace = currentSpace;
        this.destinationSpace = destinationSpace;
    }

    public Move(Piece piece, Space currentSpace, Space destinationSpace, Game game) {
        this.piece = piece;
        this.currentSpace = currentSpace;
        this.destinationSpace = destinationSpace;
        this.game = game;
    }

    public Move(Piece piece, Space destinationSpace) {
        this.piece = piece;
        this.destinationSpace = destinationSpace;
    }

    // Ref.: https://stackoverflow.com/a/51014378
    public Move() {
    }

    public boolean validate() {
        // TODO
        //  0 - Square: ensure the move is within the field -- this belongs to the `Square` class
        //  1 - Piece: general rules
        //  2 - Piece: if does not comply with general rules, check specific rules if eligible
        //    e.g. for King / Rook - check if ever moved (if castling is attempted);
        //    think about Pawn
        //  3 - Square: check if cell is not occupied by piece of same color
        //  4 - Piece: validate if checkmate
        //  other checks?

        // TODO IllegalArgumentException
        return true;
    }

    // TODO ensure enums are properly created
    //  Hibernate was once bad with enums, ref.: https://stackoverflow.com/a/735762
    // TODO separate out to DTO
    @JsonGetter("pieceTitle")
    @Enumerated(EnumType.STRING)
    public String getPieceTitle() {
        return piece.getType().getShortTitle();
    }

    @JsonGetter("spaceFile")
    @Enumerated(EnumType.STRING)
    public String getSpaceFile() {
        return destinationSpace.getFile().getShortTitle();
    }

    @JsonGetter("spaceRank")
    @Enumerated(EnumType.STRING)
    public String getSpaceRank() {
        return destinationSpace.getRank().getShortTitle();
    }

    @JsonGetter("destinationSpace")
    public Space getDestinationSpace() {
        return destinationSpace;
    }

    @JsonSetter("destinationSpace")
    public void setDestinationSpace(Space destinationSpace) {
        this.destinationSpace = destinationSpace;
    }

    public Space getCurrentSpace() {
        return currentSpace;
    }

    public void setCurrentSpace(Space currentSpace) {
        this.currentSpace = currentSpace;
    }

    @JsonGetter("piece")
    public Piece getPiece() {
        return piece;
    }

    @JsonSetter("piece")
    public void setPiece(Piece piece) {
        this.piece = piece;
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

    public Player getPlayer() {
        return player;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    @JsonSetter("pieceTitle")
    public void setPieceTitle(String pieceTitle) {
        this.pieceTitle = pieceTitle;
    }

    @JsonSetter("spaceFile")
    public void setSpaceFile(String spaceFile) {
        this.spaceFile = spaceFile;
    }

    @JsonSetter("spaceRank")
    public void setSpaceRank(String spaceRank) {
        this.spaceRank = spaceRank;
    }

    @Override
    public String toString() {
        return "Move{" +
                "piece=" + piece +
                ", currentSpace=" + currentSpace +
                ", destinationSpace=" + destinationSpace +
                '}';
    }
}
