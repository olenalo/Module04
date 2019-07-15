package com.alevel.module.model.chessboard;

import com.alevel.module.model.chessboard.configs.File;
import com.alevel.module.model.chessboard.configs.Rank;
import com.alevel.module.model.game.Game;
import com.alevel.module.model.game.Player;
import com.alevel.module.model.piece.Piece;
import com.alevel.module.model.piece.pieces.*;

import javax.persistence.*;


@SqlResultSetMapping(
    name="MoveResult",
    classes={
        @ConstructorResult(
            targetClass=Move.class,
            columns={
                @ColumnResult(name="id", type=Long.class),
                @ColumnResult(name="current_space_file", type=String.class),
                @ColumnResult(name="current_space_rank", type=String.class),
                @ColumnResult(name="destination_space_file", type=String.class),
                @ColumnResult(name="destination_space_rank", type=String.class),
                @ColumnResult(name="game_id", type=Long.class),
                @ColumnResult(name="piece_title", type=String.class),
                @ColumnResult(name="player_id", type=Long.class)})})
@NamedNativeQuery(name = "Move.findByGameId",  // TODO rewrite with @JoinTable, @JoinColumn, e.g. https://stackoverflow.com/q/949996
        query = "select * from moves move0_ left outer join games game1_ on move0_.game_id=game1_.id where game1_.id=?",
        resultSetMapping = "MoveResult")
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

    public Move(Piece piece, Space currentSpace, Space destinationSpace, Game game) {
        this.piece = piece;
        this.currentSpace = currentSpace;
        this.destinationSpace = destinationSpace;
        this.game = game;
    }

    public Move(Piece piece, Space currentSpace, Space destinationSpace) {
        this.piece = piece;
        this.currentSpace = currentSpace;
        this.destinationSpace = destinationSpace;
    }

    public Move(Piece piece, Space currentSpace, Space destinationSpace, Player player) {
        this.piece = piece;
        this.currentSpace = currentSpace;
        this.destinationSpace = destinationSpace;
        this.player = player;
    }

    // Required for a model mapper
    public Move() {
    }

    public Move(Long id,
                String currentSpaceFile,
                String currentSpaceRank,
                String destinationSpaceFile,
                String destinationSpaceRank,
                Long gameId,
                String pieceTitle,
                Long playerId) {
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
        // Set other significant data
        this.pieceTitle = this.piece.getType().getShortTitle();
        this.currentSpaceFile = this.currentSpace.getFile().getShortTitle();
        this.currentSpaceRank = this.currentSpace.getRank().getShortTitle();
        this.destinationSpaceFile = this.destinationSpace.getFile().getShortTitle();
        this.destinationSpaceRank = this.destinationSpace.getRank().getShortTitle();
        this.player = new Player(playerId);
        this.game = new Game(gameId);
    }

    // FIXME enum types in the db tables
    //  Hibernate with enums, ref.: https://stackoverflow.com/a/735762
    //  Add @Column - see the example solution: https://vladmihalcea.com/the-best-way-to-map-an-enum-type-with-jpa-and-hibernate/
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
                "id=" + id +
                ", game=" + game +
                ", player=" + player +
                ", piece=" + piece +
                ", currentSpace=" + currentSpace +
                ", destinationSpace=" + destinationSpace +
                ", pieceTitle='" + pieceTitle + '\'' +
                ", destinationSpaceFile='" + destinationSpaceFile + '\'' +
                ", destinationSpaceRank='" + destinationSpaceRank + '\'' +
                ", currentSpaceFile='" + currentSpaceFile + '\'' +
                ", currentSpaceRank='" + currentSpaceRank + '\'' +
                '}';
    }
}
