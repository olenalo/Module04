package com.alevel.module.model.game;

import com.alevel.module.model.chessboard.Move;
import com.alevel.module.model.piece.configs.Color;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// TODO consider renaming to User
// TODO use Lombok
// TODO introduce PlayerDto as a nested structure instead of Player
//  to get rid of JSON annotations here
@JsonIgnoreProperties({"username",
                       "firstName",
                       "secondName",
                       "email",
                       "password",
                       "firstPlayerGames",
                       "secondPlayerGames",
                       "piecesColor",
                       "moves"})
@Entity
@Table(name="players")
public class Player {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", unique = true)
    @NotNull
    @NotEmpty
    private String username;

    // Can be provided by a user later
    @Column(name = "fname")
    private String firstName;

    // Can be provided by a user later
    @Column(name = "sname")
    private String secondName;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    // TODO roles (consider creating a dedicated entity)
    // @Column(name = "roles")
    // private List<UserRoles> roles;

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

    public Player(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Player(Long id) {
        this.id = id;
    }

    public Player() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        // TODO decode
        return password;
    }

    public void setPassword(String password) {
        // TODO encode
        this.password = password;
    }

    /**
     * Define if equal to another object.
     *
     * Users won't be ever able to change the username.
     *
     * @param o an object the current object is being compared to.
     * @return {@code true} if the arguments are equal to each other
     *  and {@code false} otherwise.
     */
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

    @Override
    public String toString() {
        return "Player{" +
                "username='" + username + '\'' +
                '}';
    }
}
