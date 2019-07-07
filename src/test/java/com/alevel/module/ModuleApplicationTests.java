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
	 - StandardChessboardBuilder - board with history: ChessboardSquarePopulator
	 - json mapping (e.g. polymorphic type `Piece`)
	 - MoveService: save a move (w/ validators); `InvalidMoveException`
	 - MoveController: test API endpoint (e.g. valid move, invalid move -> error code and message)
	 */

	@Test
	public void contextLoads() {
	}

}
