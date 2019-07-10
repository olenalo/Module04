package com.alevel.module.model.chessboard;

import com.alevel.module.model.chessboard.configs.File;
import com.alevel.module.model.chessboard.configs.Rank;
import com.alevel.module.model.game.Game;
import com.alevel.module.model.game.Player;
import com.alevel.module.model.piece.Piece;
import com.alevel.module.model.piece.configs.Color;
import com.alevel.module.model.piece.configs.Type;
import com.alevel.module.model.piece.pieces.*;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import javax.persistence.*;

import static com.alevel.module.model.piece.configs.Type.KING;

/**
 * TODO add docstring
 *
 * Only valid move will be saved to the db.
 */
@JsonIgnoreProperties({"pieceTitle",
                       "destinationSpaceFile",
                       "destinationSpaceRank",
                       "currentSpaceFile",
                       "currentSpaceRank"})
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

    @JsonProperty("currentSpace")
    @Transient
    private Space currentSpace;

    @JsonProperty("destinationSpace")
    @Transient
    private Space destinationSpace;

    // TODO disallow null values whenever needed, throughout the project (JPA)
    // TODO consider making enums separate entities (piece title, file, rank)
    @Column(name = "pieceTitle")
    private String pieceTitle;

    @Column(name = "destinationSpaceFile")
    private String destinationSpaceFile;

    @Column(name = "destinationSpaceRank")
    private String destinationSpaceRank;

    @Column(name = "currentSpaceFile")
    private String currentSpaceFile;

    @Column(name = "currentSpaceRank")
    private String currentSpaceRank;

    // TODO adda timestamp field

    // TODO review constructors (do we need all of them?)
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

    public Move(Piece piece, Space currentSpace, Space destinationSpace) {
        this.piece = piece;
        this.currentSpace = currentSpace;
        this.destinationSpace = destinationSpace;
    }

    // Ref.: https://stackoverflow.com/a/51014378
    public Move() {
    }

    // Hibernate:
    // select move0_.id as id1_1_,
    // move0_.game_id as game_id5_1_,
    // move0_.piece_title as piece_ti2_1_,
    // move0_.player_id as player_i6_1_,
    // move0_.space_file as space_fi3_1_,
    // move0_.space_rank as space_ra4_1_
    // from moves move0_ left outer join games game1_ on move0_.game_id=game1_.id where game1_.id=?
    // TODO remove (didn't help for now)
    // TODO update (destination, current spaces)
    /*
    public Move(Long id, String gameId, String pieceTitle, String playerId, String spaceFile, String spaceRank) {
        switch(pieceTitle) {
            // TODO consider getting rid of magic strings
            case "king":
                this.piece = new King();
                break;
            case "bishop":
                this.piece = new Bishop();
                break;
            case "knight":
                this.piece = new Knight();
                break;
            case "pawn":
                this.piece = new Pawn();
                break;
            case "queen":
                this.piece = new Queen();
                break;
            case "rook":
                this.piece = new Rook();
                break;
        }
        this.id = id;
        this.destinationSpace = new Space(File.create(spaceFile), Rank.create(spaceRank));
    }
     */

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

    @JsonGetter("destinationSpaceFile")
    @Enumerated(EnumType.STRING)
    public String getDestinationSpaceFile() {
        return destinationSpace.getFile().getShortTitle();
    }

    @JsonGetter("destinationSpaceRank")
    @Enumerated(EnumType.STRING)
    public String getDestinationSpaceRank() {
        return destinationSpace.getRank().getShortTitle();
    }

    @JsonGetter("currentSpaceFile")
    @Enumerated(EnumType.STRING)
    public String getCurrentSpaceFile() {
        return currentSpace.getFile().getShortTitle();
    }

    @JsonGetter("currentSpaceRank")
    @Enumerated(EnumType.STRING)
    public String getCurrentSpaceRank() {
        return currentSpace.getRank().getShortTitle();
    }

    @JsonGetter("destinationSpace")
    public Space getDestinationSpace() {
        return destinationSpace;
    }

    @JsonSetter("destinationSpace")
    public void setDestinationSpace(Space destinationSpace) {
        this.destinationSpace = destinationSpace;
    }

    @JsonGetter("currentSpace")
    public Space getCurrentSpace() {
        return currentSpace;
    }

    @JsonSetter("currentSpace")
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

    @JsonSetter("destinationSpaceFile")
    public void setDestinationSpaceFile(String spaceFile) {
        this.destinationSpaceFile = spaceFile;
    }

    @JsonSetter("destinationSpaceRank")
    public void setDestinationSpaceRank(String spaceRank) {
        this.destinationSpaceRank = spaceRank;
    }

    @JsonSetter("currentSpaceFile")
    public void setCurrentSpaceFile(String spaceFile) {
        this.currentSpaceFile = spaceFile;
    }

    @JsonSetter("currentSpaceRank")
    public void setCurrentSpaceRank(String spaceRank) {
        this.currentSpaceRank = spaceRank;
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
