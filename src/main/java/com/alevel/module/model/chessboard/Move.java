package com.alevel.module.model.chessboard;

import com.alevel.module.model.game.Game;
import com.alevel.module.model.game.Player;
import com.alevel.module.model.piece.Piece;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import javax.persistence.*;

/**
 * TODO add docstring
 *
 * A valid move only will be saved to the db.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
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
    @Enumerated(EnumType.STRING)
    public String getPieceTitle() {
        return piece.getType().getShortTitle();
    }

    @Enumerated(EnumType.STRING)
    public String getSpaceFile() {
        return destinationSpace.getFile().getShortTitle();
    }

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

    @Override
    public String toString() {
        return "Move{" +
                "piece=" + piece +
                ", currentSpace=" + currentSpace +
                ", destinationSpace=" + destinationSpace +
                '}';
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


}
