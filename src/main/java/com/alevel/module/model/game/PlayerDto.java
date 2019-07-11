package com.alevel.module.model.game;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@JsonIgnoreProperties({"id",
                       "fname",
                       "sname",
                       "email",
                       "moves",
                       "password"})  // "roles"})
@JsonDeserialize(as=PlayerDto.class)
public class PlayerDto {
    @JsonProperty("id")
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("username")
    @NotNull
    @NotEmpty
    private String username;

    // Can be provided by a user later
    @JsonProperty("fname")
    private String firstName;

    // Can be provided by a user later
    @JsonProperty("sname")
    private String secondName;

    @JsonProperty("email")
    private String email;

    @JsonProperty("password")
    private String password;

    public PlayerDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "PlayerDto{" +
                "username='" + username + '\'' +
                '}';
    }
}
