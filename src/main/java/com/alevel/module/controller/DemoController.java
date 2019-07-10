package com.alevel.module.controller;

import com.alevel.module.model.chessboard.Chessboard;
import com.alevel.module.model.chessboard.Move;
import com.alevel.module.model.chessboard.Space;
import com.alevel.module.model.chessboard.Square;
import com.alevel.module.model.chessboard.configs.File;
import com.alevel.module.model.chessboard.configs.Rank;
import com.alevel.module.model.game.initializers.StandardChessboardBuilder;
import com.alevel.module.model.piece.Piece;
import com.alevel.module.model.piece.pieces.King;
import com.alevel.module.model.piece.pieces.Knight;
import com.alevel.module.model.piece.pieces.Queen;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static com.alevel.module.model.chessboard.configs.File.A;
import static com.alevel.module.model.chessboard.configs.File.B;
import static com.alevel.module.model.chessboard.configs.Rank.*;
import static com.alevel.module.model.piece.configs.Color.BLACK;
import static com.alevel.module.model.piece.configs.Color.WHITE;


@RestController
@RequestMapping("/demo")
public class DemoController {

    // TODO remove
    private static Chessboard createDummyChessboard() {
        List<Square> squares = new ArrayList<>();
        squares.add(new Square(new Space(File.A, Rank.ONE)));
        squares.add(new Square(new Space(File.B, Rank.ONE)));
        squares.add(new Square(new Space(File.C, Rank.ONE)));
        squares.add(new Square(new Space(File.D, Rank.ONE)));
        squares.add(new Square(new Space(File.E, Rank.ONE)));
        squares.add(new Square(new Space(File.F, Rank.ONE)));
        squares.add(new Square(new Space(File.G, Rank.ONE)));
        squares.add(new Square(new Space(File.H, Rank.ONE)));
        squares.add(new Square(new Space(File.A, Rank.TWO)));
        squares.add(new Square(new Space(File.B, Rank.TWO)));
        squares.add(new Square(new Space(File.C, Rank.TWO)));
        squares.add(new Square(new Space(File.D, Rank.TWO)));
        squares.add(new Square(new Space(File.E, Rank.TWO)));
        squares.add(new Square(new Space(File.F, Rank.TWO)));
        squares.add(new Square(new Space(File.G, Rank.TWO)));
        squares.add(new Square(new Space(File.H, Rank.TWO)));
        return new Chessboard(squares);
    }

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
    public boolean makeMove(@RequestBody Move move) {
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

        // Demo
        move.setCurrentSpace(new Space(A, ONE));
        // Uncomment to check an error; or just pass an invalid file/rank
        // move.setCurrentSpace(new Space(A, FOUR));

        // Make a move
        return move.getPiece().doMove(move, chessboard);
    }

}
