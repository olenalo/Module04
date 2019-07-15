package com.alevel.module.model.game;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@JsonDeserialize(as=PlayerDto.class)
public class PlayerDto {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("username")
    @NotNull
    @NotEmpty
    private String username;

    // Can be provided by a user later
    private String firstName;

    // Can be provided by a user later
    private String secondName;

    @JsonProperty(value="email", access = JsonProperty.Access.WRITE_ONLY)
    @NotNull
    @NotEmpty
    private String email;

    @JsonProperty(value="password", access = JsonProperty.Access.WRITE_ONLY)
    @NotNull
    @NotEmpty
    private String password;

    public PlayerDto() {
    }

    @JsonIgnore
    public Long getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @JsonProperty("sname")
    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    @JsonIgnore
    public String getFirstName() {
        return firstName;
    }

    @JsonProperty("fname")
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @JsonIgnore
    public String getSecondName() {
        return secondName;
    }

    @JsonIgnore
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @JsonIgnore  // Ignore for serialization only
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        // Make it as much detailed as possible (to debug deserialization)
        return "PlayerDto{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
