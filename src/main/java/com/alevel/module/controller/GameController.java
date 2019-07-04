package com.alevel.module.controller;

import com.alevel.module.persistence.chessboard.Chessboard;
import com.alevel.module.persistence.chessboard.Move;
import com.alevel.module.persistence.chessboard.Space;
import com.alevel.module.persistence.chessboard.Square;
import com.alevel.module.persistence.game.Game;
import com.alevel.module.persistence.game.Player;
import com.alevel.module.persistence.game.initializers.ChessboardBuilder;
import com.alevel.module.persistence.game.initializers.StandardChessboardBuilder;
import com.alevel.module.persistence.piece.Piece;
import com.alevel.module.persistence.piece.pieces.King;
import com.alevel.module.persistence.piece.pieces.Knight;
import com.alevel.module.persistence.piece.pieces.Queen;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static com.alevel.module.controller.utils.GameControllerUtils.createDummyChessboard;
import static com.alevel.module.persistence.chessboard.configs.File.A;
import static com.alevel.module.persistence.chessboard.configs.File.B;
import static com.alevel.module.persistence.chessboard.configs.Rank.*;
import static com.alevel.module.persistence.piece.configs.Color.BLACK;
import static com.alevel.module.persistence.piece.configs.Color.WHITE;

// TODO get rid of wildcard imports everywhere

@RestController
@RequestMapping("/chess")
public class GameController {

    @PostMapping("/demoMove")
    public boolean demoMove() {
        Piece piece = new King(WHITE);
        // Example of valid movement
        Space currentSpace = new Space(A, ONE);
        Space destinationSpace = new Space(B, TWO);
        // Example of invalid movement
        /*
        Space currentSpace = new Space(A, ONE);
        Space destinationSpace = new Space(B, FOUR);
        */
        Move move = new Move(piece, currentSpace, destinationSpace);
        return move.getPiece().doMove(move, createDummyChessboard());
    }

    @PostMapping("/makeMove")
    // TODO response: statuses, custom codes and messages based on validation results (IllegalArgumentException?)
    public boolean makeMove(@RequestBody Move move) {
        // TODO get current user (player) from request
        // TODO get an open game the user is currently playing

        // Fetch moves history
        Move move1 = new Move(new Knight(WHITE), new Space(A, ONE), new Space(A, TWO));
        Move move2 = new Move(new King(WHITE), new Space(A, ONE), new Space(A, THREE));
        Move move3 = new Move(new Queen(BLACK), new Space(A, ONE), new Space(A, FOUR));

        // Build squares w/ pieces (build up states from moves history)
        Square square1 = new Square(move1.getDestinationSpace(), move1.getPiece());
        Square square2 = new Square(move2.getDestinationSpace(), move2.getPiece());
        Square square3 = new Square(move3.getDestinationSpace(), move3.getPiece());
        List<Square> squares = new ArrayList<>();
        // Comment out to test chessboard initial population with pieces
        /*
        squares.add(square1);
        squares.add(square2);
        squares.add(square3);
         */

        // Build the game's chessboard to ease access to states
        StandardChessboardBuilder chessboardBuilder = new StandardChessboardBuilder();
        Chessboard chessboard = chessboardBuilder
                .addOccupiedSquares(squares)
                .addEmptySquares()
                .build();
        System.out.println(chessboard);

        // Make a move
        return move.getPiece().doMove(move, chessboard);
    }

}
