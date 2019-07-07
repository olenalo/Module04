package com.alevel.module.controller.exceptions;

import com.alevel.module.model.chessboard.Move;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class InvalidMoveException extends RuntimeException {

    public InvalidMoveException(Move move) {
        super("Move '" + move + "' is invalid.");
    }

}
