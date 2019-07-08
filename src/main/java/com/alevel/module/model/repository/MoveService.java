package com.alevel.module.model.repository;

import com.alevel.module.controller.exceptions.InvalidMoveException;
import com.alevel.module.model.chessboard.Chessboard;
import com.alevel.module.model.chessboard.Move;
import com.alevel.module.model.chessboard.Space;
import com.alevel.module.model.chessboard.Square;
import com.alevel.module.model.game.initializers.StandardChessboardBuilder;
import com.alevel.module.model.piece.pieces.King;
import com.alevel.module.model.piece.pieces.Knight;
import com.alevel.module.model.piece.pieces.Queen;
import com.alevel.module.service.MoveOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.alevel.module.model.chessboard.configs.File.A;
import static com.alevel.module.model.chessboard.configs.Rank.*;
import static com.alevel.module.model.piece.configs.Color.BLACK;
import static com.alevel.module.model.piece.configs.Color.WHITE;

@Service
public class MoveService implements MoveOperations {

    private MoveRepository moveRepository;

    @Autowired
    public MoveService(MoveRepository moveRepository) {
        this.moveRepository = moveRepository;
    }

    @Override
    public List<Move> findAll() {
        return moveRepository.findAll();
    }

    @Override
    public Optional<Move> find(Long id) {
        return moveRepository.findById(id);
    }

    @Override
    public void update(Long id, Move move) {
        // TODO
    }

    @Override
    public Long save(Move move) throws IllegalArgumentException {

        // TODO Fetch moves history and build up a chessboard
        //  - If s game just started, build a chessboard right away and start validating the move (build up moves etc)
        //  ---- figure out a way to define if a game just started (cache?)
        //  - MoveOperations | MoveService - if game has been ongoing, findAll by the Game obj.
        //  - Extract latest only (per piece types)
        //  - define if captured (isCaptured), take only non-captured pieces into account
        //  ---- Move: store (in the db) if captured opponent's piece to avoid re-validation upon every request
        //  - Build up moves as demonstrated below (might not be needed).
        //  - Build up squares w/ pieces (build up states from moves history)
        //  ---- define current spaces for each piece
        //  - Cache it all

        // Demo: Fetch moves history
        Move move1 = new Move(new Knight(WHITE), new Space(A, ONE), new Space(A, TWO));
        Move move2 = new Move(new King(WHITE), new Space(A, ONE), new Space(A, THREE));
        Move move3 = new Move(new Queen(BLACK), new Space(A, ONE), new Space(A, FOUR));

        // Demo: Build squares w/ pieces (build up states from moves history)
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

        // Build the game's chessboard to provide the access to states
        StandardChessboardBuilder chessboardBuilder = new StandardChessboardBuilder();
        Chessboard chessboard = chessboardBuilder
                .addOccupiedSquares(squares)
                .addEmptySquares()
                .build();
        System.out.println("The game chessboard has been built: \n" + chessboard);

        // Demo
        move.setCurrentSpace(new Space(A, ONE));
        // Uncomment to check an error; or just pass an invalid file/rank
        // move.setCurrentSpace(new Space(A, FOUR));

        // TODO Add validators / evaluators: define current spaces of pieces (visitors), add other validators
        //  - Implement visitors to look up players' pieces' states from a chessboard
        //  - Add other validators (compliance with specific rules, if empty, within-the-field movement, etc)
        //  - Implement capturing (write to the previously implemented isCaptured - update a `move` to store (setIsCaptured))
        //  - Add evaluators
        //  ---- check
        //  ---- checkmate
        //  ---- draw
        //  - Closure (if checkmate or draw, store results and finish the game).

        // Make a move
        // TODO models: check if checkmate, check or draw (with respective consequences e.g. define the winner)
        if (move.getPiece().doMove(move, chessboard)) {
            return moveRepository.save(move).getId() ;
        } else {
            throw new InvalidMoveException(move);
        }
    }

    @Override
    public void delete(Long id) {
        moveRepository.deleteById(id);
    }
}
