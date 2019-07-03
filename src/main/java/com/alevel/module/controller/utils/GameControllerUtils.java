package com.alevel.module.controller.utils;

import com.alevel.module.persistence.chessboard.Chessboard;
import com.alevel.module.persistence.chessboard.Space;
import com.alevel.module.persistence.chessboard.Square;
import com.alevel.module.persistence.chessboard.configs.File;
import com.alevel.module.persistence.chessboard.configs.Rank;

import java.util.ArrayList;
import java.util.List;

public class GameControllerUtils {

    public static Chessboard createDummyChessboard() {
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
}
