package com.alevel.module;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ModuleApplicationTests {

	/*
	TODO Test cases (first thing to test; for now, tested manually):
	 - StandardChessboardBuilder - board without history: populateKingRowSquare; also, pawn rows
	 - json mapping (e.g. polymorphic type `Piece`)
	 - MoveService: save a move (w/ validators); `InvalidMoveException`
	 - MoveService | MoveController: 'IllegalArgumentException' e.g. "Please provide a correct Rank value" (enum)
	 - MoveController: test API endpoint (e.g. valid move, invalid move -> error code and message)
	 - MoveController" authorization
	 - PlayerController: cannot login if not registered (401)
	 - Integration: Play Fool's Mate to speed up testing, ref.: https://www.chess.com/article/view/the-fastest-possible-checkmate-in-chess
	 */

	@Test
	public void contextLoads() {
	}

	/*
	Brand-new chessboard:

		Chessboard{squares=[
		  Square{space=Space{file=a, rank=1}, piece=Rook{color=white, type=rook}}
		, Square{space=Space{file=b, rank=1}, piece=Knight{color=white, type=knight}}
		, Square{space=Space{file=c, rank=1}, piece=Bishop{color=white, type=bishop}}
		, Square{space=Space{file=d, rank=1}, piece=Queen{color=white, type=queen}}
		, Square{space=Space{file=e, rank=1}, piece=King{color=white, type=king}}
		, Square{space=Space{file=f, rank=1}, piece=Bishop{color=white, type=bishop}}
		, Square{space=Space{file=g, rank=1}, piece=Knight{color=white, type=knight}}
		, Square{space=Space{file=h, rank=1}, piece=Rook{color=white, type=rook}}
		, Square{space=Space{file=a, rank=2}, piece=Pawn{color=white, type=pawn}}
		, Square{space=Space{file=b, rank=2}, piece=Pawn{color=white, type=pawn}}
		, Square{space=Space{file=c, rank=2}, piece=Pawn{color=white, type=pawn}}
		, Square{space=Space{file=d, rank=2}, piece=Pawn{color=white, type=pawn}}
		, Square{space=Space{file=e, rank=2}, piece=Pawn{color=white, type=pawn}}
		, Square{space=Space{file=f, rank=2}, piece=Pawn{color=white, type=pawn}}
		, Square{space=Space{file=g, rank=2}, piece=Pawn{color=white, type=pawn}}
		, Square{space=Space{file=h, rank=2}, piece=Pawn{color=white, type=pawn}}
		, Square{space=Space{file=a, rank=3}, piece=null}
		, Square{space=Space{file=b, rank=3}, piece=null}
		, Square{space=Space{file=c, rank=3}, piece=null}
		, Square{space=Space{file=d, rank=3}, piece=null}
		, Square{space=Space{file=e, rank=3}, piece=null}
		, Square{space=Space{file=f, rank=3}, piece=null}
		, Square{space=Space{file=g, rank=3}, piece=null}
		, Square{space=Space{file=h, rank=3}, piece=null}
		, Square{space=Space{file=a, rank=4}, piece=null}
		, Square{space=Space{file=b, rank=4}, piece=null}
		, Square{space=Space{file=c, rank=4}, piece=null}
		, Square{space=Space{file=d, rank=4}, piece=null}
		, Square{space=Space{file=e, rank=4}, piece=null}
		, Square{space=Space{file=f, rank=4}, piece=null}
		, Square{space=Space{file=g, rank=4}, piece=null}
		, Square{space=Space{file=h, rank=4}, piece=null}
		, Square{space=Space{file=a, rank=5}, piece=null}
		, Square{space=Space{file=b, rank=5}, piece=null}
		, Square{space=Space{file=c, rank=5}, piece=null}
		, Square{space=Space{file=d, rank=5}, piece=null}
		, Square{space=Space{file=e, rank=5}, piece=null}
		, Square{space=Space{file=f, rank=5}, piece=null}
		, Square{space=Space{file=g, rank=5}, piece=null}
		, Square{space=Space{file=h, rank=5}, piece=null}
		, Square{space=Space{file=a, rank=6}, piece=null}
		, Square{space=Space{file=b, rank=6}, piece=null}
		, Square{space=Space{file=c, rank=6}, piece=null}
		, Square{space=Space{file=d, rank=6}, piece=null}
		, Square{space=Space{file=e, rank=6}, piece=null}
		, Square{space=Space{file=f, rank=6}, piece=null}
		, Square{space=Space{file=g, rank=6}, piece=null}
		, Square{space=Space{file=h, rank=6}, piece=null}
		, Square{space=Space{file=a, rank=7}, piece=Pawn{color=black, type=pawn}}
		, Square{space=Space{file=b, rank=7}, piece=Pawn{color=black, type=pawn}}
		, Square{space=Space{file=c, rank=7}, piece=Pawn{color=black, type=pawn}}
		, Square{space=Space{file=d, rank=7}, piece=Pawn{color=black, type=pawn}}
		, Square{space=Space{file=e, rank=7}, piece=Pawn{color=black, type=pawn}}
		, Square{space=Space{file=f, rank=7}, piece=Pawn{color=black, type=pawn}}
		, Square{space=Space{file=g, rank=7}, piece=Pawn{color=black, type=pawn}}
		, Square{space=Space{file=h, rank=7}, piece=Pawn{color=black, type=pawn}}
		, Square{space=Space{file=a, rank=8}, piece=Rook{color=black, type=rook}}
		, Square{space=Space{file=b, rank=8}, piece=Knight{color=black, type=knight}}
		, Square{space=Space{file=c, rank=8}, piece=Bishop{color=black, type=bishop}}
		, Square{space=Space{file=d, rank=8}, piece=Queen{color=black, type=queen}}
		, Square{space=Space{file=e, rank=8}, piece=King{color=black, type=king}}
		, Square{space=Space{file=f, rank=8}, piece=Bishop{color=black, type=bishop}}
		, Square{space=Space{file=g, rank=8}, piece=Knight{color=black, type=knight}}
		, Square{space=Space{file=h, rank=8}, piece=Rook{color=black, type=rook}}
		]}
	 */


}
