package com.alevel.module.model.repository;

import com.alevel.module.controller.exceptions.InvalidMoveException;
import com.alevel.module.model.chessboard.Chessboard;
import com.alevel.module.model.chessboard.Move;
import com.alevel.module.model.chessboard.Space;
import com.alevel.module.model.chessboard.Square;
import com.alevel.module.model.game.Game;
import com.alevel.module.model.game.initializers.StandardChessboardBuilder;
import com.alevel.module.service.MoveOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.alevel.module.model.chessboard.configs.File.A;
import static com.alevel.module.model.chessboard.configs.Rank.*;

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
    public List<Move> findAll(Game game) {
        return moveRepository.findByGame(game);
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

        // Fetch moves history
        // TODO Extract latest only (per piece types) when database querying, ref: https://stackoverflow.com/a/20283256
        // TODO define if captured (isCaptured), take only non-captured pieces into account
        List<Move> moves = findAll(move.getGame());

        // Build up states from moves history (squares w/ pieces)
        List<Square> squares = new ArrayList<>();
        if (!moves.isEmpty()) {
            for (Move m : moves) {
                squares.add(new Square(m.getDestinationSpace(), m.getPiece()));  // TODO handle nullables
            }
        }

        // Build the game's chessboard to provide the access to states
        StandardChessboardBuilder chessboardBuilder = new StandardChessboardBuilder();
        Chessboard chessboard = chessboardBuilder
                .addOccupiedSquares(squares) // TODO think of a better way to check squares isEmpty
                .addEmptySquares()
                .build();
        System.out.println("The game chessboard has been built: \n" + chessboard);

        // TODO Implement visitors to look up players' pieces' states from a chessboard
        // TODO Define current spaces for each piece (by visitors)
        // Demo
        move.setCurrentSpace(new Space(A, ONE));

        // Validate and make a move
        // TODO models: check if checkmate, check or draw (with respective consequences e.g. define the winner)
        if (move.getPiece().doMove(move, chessboard)) {

            // TODO Add other validators (compliance with specific rules, if empty, within-the-field movement, etc)

            // TODO Add game state evaluators (check, checkmate, draw)

            // TODO Move: store (in the db) if captured opponent's piece to avoid re-validation upon every request

            // TODO Closure (if checkmate or draw, store results and finish the game).
            // TODO Implement capturing (write to the previously implemented isCaptured - update a `move` to store (setIsCaptured))

            // TODO Cache the updated states
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
