package com.alevel.module.service;

import com.alevel.module.controller.exceptions.GameNotFoundException;
import com.alevel.module.controller.exceptions.InvalidMoveException;
import com.alevel.module.model.chessboard.Chessboard;
import com.alevel.module.model.chessboard.Move;
import com.alevel.module.model.game.Game;
import com.alevel.module.model.game.initializers.StandardChessboardBuilder;
import com.alevel.module.service.operation.MoveOperations;
import com.alevel.module.service.repository.GameRepository;
import com.alevel.module.service.repository.MoveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;
import java.util.Optional;

@Service
public class MoveService implements MoveOperations {

    private MoveRepository moveRepository;
    private GameRepository gameRepository;

    @Autowired
    public MoveService(MoveRepository moveRepository, GameRepository gameRepository) {
        this.moveRepository = moveRepository;
        this.gameRepository = gameRepository;
    }

    @Override
    public List<Move> findAll() {
        return moveRepository.findAll();
    }

    @Override
    public List<Move> findAll(Game game) {
        return moveRepository.findByGameId(game.getId());
    }

    @Override
    public Optional<Move> find(Long id) {
        return moveRepository.findById(id);
    }

    @Override
    public void update(Long id, Move move) {
        throw new NotImplementedException();
    }

    @Override
    public Long save(Move move) throws InvalidMoveException, GameNotFoundException {
        // Fetch moves history
        List<Move> gameMoves = findAll(move.getGame());

        // Check the game the user is currently playing (if none, raise GameNotFoundException)
        Optional<Game> gameOptional = gameRepository.findById(move.getGame().getId());
        Game game = gameOptional.orElse(null);
        if (game == null) {
            throw new GameNotFoundException(move.getGame().getId());
        }

        // Build the game chessboard
        // TODO think of better ways (maye moving in all to Chessboard directly):
        //  this is not a builder: we don't pass params one by one
        Chessboard chessboard = new StandardChessboardBuilder(gameMoves, game).build();
        System.out.println("The game chessboard has been built: \n" + chessboard);

        // Validate and make a move, evaluate game situation
        // TODO Add other validators:
        //  finish compliance validation with general rules,
        //  validate compliance with specific rules,
        //  King: add validateCheck(): ensure the move doesn't put a King in check
        if (move.getPiece().validateMove(move, chessboard)) {
            System.out.println("Going to save a move: " + move);
            Long id = moveRepository.save(move).getId();
            // Evaluate game situation
            // TODO Add isDraw() evaluation, ref. https://en.wikipedia.org/wiki/Draw_(chess)
            // TODO Closure (store results and finish the game).
            if (chessboard.isCheckMate(move)) {
                return null;
            } else {
                return id;
            }
        } else {
            throw new InvalidMoveException(move);
        }
    }

    @Override
    public void delete(Long id) {
        moveRepository.deleteById(id);
    }
}
