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
 * Move model.
 *
 * Only valid move will be saved to the db.
 */
@Entity
@Table(name="moves")
public class Move {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn()
    private Game game;

    @ManyToOne
    @JoinColumn()
    private Player player;

    @Transient
    private Piece piece;

    @Transient
    private Space currentSpace;

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

    // TODO add a timestamp field

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

    // Required for model mapper
    public Move() {
    }

    // Hibernate:
    // select
    //   move0_.id as id1_1_,
    //   move0_.current_space_file as current_2_1_,
    //   move0_.current_space_rank as current_3_1_,
    //   move0_.destination_space_file as destinat4_1_,
    //   move0_.destination_space_rank as destinat5_1_,
    //   move0_.game_id as game_id7_1_,
    //   move0_.piece_title as piece_ti6_1_,
    //   move0_.player_id as player_i8_1_
    // from moves move0_ left outer join games game1_ on move0_.game_id=game1_.id where game1_.id=?
    public Move(Long id,
                String currentSpaceFile,
                String currentSpaceRank,
                String destinationSpaceFile,
                String destinationSpaceRank,
                String gameId,
                String pieceTitle,
                String playerId) {
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
        this.destinationSpace = new Space(File.create(destinationSpaceFile), Rank.create(destinationSpaceRank));
        this.currentSpace = new Space(File.create(currentSpaceFile), Rank.create(currentSpaceRank));
    }


    // FIXME enum types in the db tables
    //  Hibernate with enums, ref.: https://stackoverflow.com/a/735762
    @Enumerated(EnumType.STRING)
    public String getPieceTitle() {
        return piece.getType().getShortTitle();
    }

    @Enumerated(EnumType.STRING)
    public String getDestinationSpaceFile() {
        return destinationSpace.getFile().getShortTitle();
    }

    @Enumerated(EnumType.STRING)
    public String getDestinationSpaceRank() {
        return destinationSpace.getRank().getShortTitle();
    }

    @Enumerated(EnumType.STRING)
    public String getCurrentSpaceFile() {
        return currentSpace.getFile().getShortTitle();
    }

    @Enumerated(EnumType.STRING)
    public String getCurrentSpaceRank() {
        return currentSpace.getRank().getShortTitle();
    }

    public Space getDestinationSpace() {
        return destinationSpace;
    }

    public void setDestinationSpace(Space destinationSpace) {
        this.destinationSpace = destinationSpace;
    }

    public Space getCurrentSpace() {
        return currentSpace;
    }

    public void setCurrentSpace(Space currentSpace) {
        this.currentSpace = currentSpace;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public void setPieceTitle(String pieceTitle) {
        this.pieceTitle = pieceTitle;
    }

    public void setDestinationSpaceFile(String spaceFile) {
        this.destinationSpaceFile = spaceFile;
    }

    public void setDestinationSpaceRank(String spaceRank) {
        this.destinationSpaceRank = spaceRank;
    }

    public void setCurrentSpaceFile(String spaceFile) {
        this.currentSpaceFile = spaceFile;
    }

    public void setCurrentSpaceRank(String spaceRank) {
        this.currentSpaceRank = spaceRank;
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

    @Override
    public String toString() {
        return "Move{" +
                "piece=" + piece +
                ", currentSpace=" + currentSpace +
                ", destinationSpace=" + destinationSpace +
                '}';
    }
}
