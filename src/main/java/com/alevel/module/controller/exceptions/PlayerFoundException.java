package com.alevel.module.controller.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// TODO use it
@ResponseStatus(HttpStatus.NOT_FOUND)
public class PlayerFoundException extends RuntimeException {

    public PlayerFoundException(Long id) {
        super("Player with id " + id + " not found");
    }

}
