package com.alevel.module.model.repository;

import com.alevel.module.controller.exceptions.InvalidMoveException;
import com.alevel.module.model.chessboard.Chessboard;
import com.alevel.module.model.chessboard.Move;
import com.alevel.module.model.chessboard.Space;
import com.alevel.module.model.game.Game;
import com.alevel.module.model.game.initializers.StandardChessboardBuilder;
import com.alevel.module.model.piece.pieces.Pawn;
import com.alevel.module.service.MoveOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.alevel.module.model.chessboard.configs.File.*;
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
    public List<Move> findAll(Game game) {
        return moveRepository.findByGameId(game.getId());
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
    public Long save(Move move) {
        // Fetch moves history
        // TODO assign colors to pieces!  (see StandardChessboardBuilder)
        List<Move> gameMoves = findAll(move.getGame());

        // Build the game chessboard
        // TODO think of better ways: this is not a builder: we don't pass params one by one
        Chessboard chessboard = new StandardChessboardBuilder(gameMoves).build();
        System.out.println("The game chessboard has been built: \n" + chessboard);

        // TODO define and set a color for current move's piece (fetch from request / check from db by player number),
        // Set other significant data to save with a current move
        move.setPieceTitle(move.getPiece().getType().getShortTitle());
        move.setCurrentSpaceFile(move.getCurrentSpace().getFile().getShortTitle());
        move.setCurrentSpaceRank(move.getCurrentSpace().getRank().getShortTitle());
        move.setDestinationSpaceFile(move.getDestinationSpace().getFile().getShortTitle());
        move.setDestinationSpaceRank(move.getDestinationSpace().getRank().getShortTitle());

        // Validate and make a move, evaluate game situation
        // TODO Add other validators
        //  compliance with specific rules,
        //  if on-the-way squares are empty (cannot jump over other pieces!),
        //  if a destination square is not occupied by piece of same color
        //  if within-the-field

        // TODO Chessboard: implement the look-up of players' pieces' states from a chessboard

        // TODO Add game state evaluators: check, checkmate, draw -
        //  ref. https://en.wikipedia.org/wiki/Draw_(chess)
        // TODO Closure (if checkmate or draw, store results and finish the game).

        // TODO Cache the updated states
        if (move.getPiece().validateMove(move, chessboard)) {
            Long id = moveRepository.save(move).getId();
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
