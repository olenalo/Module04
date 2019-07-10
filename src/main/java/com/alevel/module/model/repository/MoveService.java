package com.alevel.module.model.repository;

import com.alevel.module.controller.exceptions.InvalidMoveException;
import com.alevel.module.model.chessboard.Chessboard;
import com.alevel.module.model.chessboard.Move;
import com.alevel.module.model.chessboard.Space;
import com.alevel.module.model.chessboard.Square;
import com.alevel.module.model.game.Game;
import com.alevel.module.model.game.initializers.StandardChessboardBuilder;
import com.alevel.module.model.piece.Piece;
import com.alevel.module.model.piece.configs.Color;
import com.alevel.module.model.piece.pieces.King;
import com.alevel.module.model.piece.pieces.Pawn;
import com.alevel.module.model.piece.pieces.Queen;
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
    public Long save(Move move) throws IllegalArgumentException {

        // Fetch moves history
        // TODO Extract latest only (per piece types) when database querying, ref: https://stackoverflow.com/a/20283256
        // TODO define if captured (isCaptured), take only non-captured pieces into account
        // FIXME each entry should contain all move's fields (now it's `id` only);
        //  consider https://stackoverflow.com/a/36329166
        List<Move> gameMoves = findAll(move.getGame());
        // Replaced with demo moves till FIX-ME gets resolved
        // Play Fool's Mate to speed up testing
        //  ref.: https://www.chess.com/article/view/the-fastest-possible-checkmate-in-chess
        gameMoves = new ArrayList<>();
        gameMoves.add(new Move(new King(WHITE), new Space(F, THREE)));
        gameMoves.add(new Move(new Pawn(BLACK), new Space(E, FIVE)));
        gameMoves.add(new Move(new Pawn(WHITE), new Space(G, FOUR)));
        // gameMoves.add(new Move(new Queen(BLACK), new Space(H, FOUR))); // expected move for a checkmate

        // Build up states from moves history (squares w/ pieces)
        List<Square> squares = new ArrayList<>();
        System.out.println("============ Moves ================");
        if (!gameMoves.isEmpty()) {
            for (Move m : gameMoves) {
                System.out.println(m);
                // TODO define (fetch from request / check from db by player number),
                //  and set a color to each move (for db-fetched moves)
                squares.add(new Square(m.getDestinationSpace(), m.getPiece()));  // TODO handle nullables
            }
        }
        System.out.println("============================");

        // Build the game chessboard
        StandardChessboardBuilder chessboardBuilder = new StandardChessboardBuilder();
        Chessboard chessboard = chessboardBuilder
                .addOccupiedSquares(squares) // TODO think of a better way to check squares isEmpty
                .addEmptySquares()
                .build();
        System.out.println("The game chessboard has been built: \n" + chessboard);

        // TODO Implement visitors to look up players' pieces' states from a chessboard
        // TODO Define current spaces for each piece (by visitors)
        // Demo
        move.setCurrentSpace(new Space(D, EIGHT));

        // Set other significant data to save with a current move
        move.setPieceTitle(move.getPiece().getType().getShortTitle());
        move.setSpaceFile(move.getDestinationSpace().getFile().getShortTitle());
        move.setSpaceRank(move.getDestinationSpace().getRank().getShortTitle());

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
