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

        // TODO models: Fetch moves history
        Move move1 = new Move(new Knight(WHITE), new Space(A, ONE), new Space(A, TWO));
        Move move2 = new Move(new King(WHITE), new Space(A, ONE), new Space(A, THREE));
        Move move3 = new Move(new Queen(BLACK), new Space(A, ONE), new Space(A, FOUR));

        // TODO models: Build squares w/ pieces (build up states from moves history)
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

        // TODO models: Build the game's chessboard to provide the access to states
        StandardChessboardBuilder chessboardBuilder = new StandardChessboardBuilder();
        Chessboard chessboard = chessboardBuilder
                .addOccupiedSquares(squares)
                .addEmptySquares()
                .build();
        System.out.println("The game chessboard has been built: \n" + chessboard);
        // TODO models: define current spaces of pieces (visitors)
        move.setCurrentSpace(new Space(A, ONE));
        // Uncomment to check an error; or just pass an invalid file/rank
        // move.setCurrentSpace(new Space(A, FOUR));

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
