package com.alevel.module.controller.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// TODO use it
@ResponseStatus(HttpStatus.NOT_FOUND)
public class GameFoundException extends RuntimeException {

    public GameFoundException(Long id) {
        super("Game with id " + id + " not found");
    }

}
