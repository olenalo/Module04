package com.alevel.module.model.piece;

import com.alevel.module.model.chessboard.Move;
import com.alevel.module.model.chessboard.Space;
import com.alevel.module.model.piece.pieces.King;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.alevel.module.model.chessboard.configs.File.*;
import static com.alevel.module.model.chessboard.configs.Rank.*;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
@SpringBootTest
public class PieceParamTest {

    private Move move;
    private List<Space> expectedSpaces;

    private Space currentSpace;
    private Space destinationSpace;

    @Before
    public void setUp() {
        // Pick any piece type
        move = new Move(new King(), currentSpace, destinationSpace);
    }

    public PieceParamTest(Space currentSpace, Space destinationSpace, List<Space> expectedSpaces) {
        this.currentSpace = currentSpace;
        this.destinationSpace = destinationSpace;
        this.expectedSpaces = expectedSpaces;
    }

    @After
    public void tearDown() {
        this.move.getPiece().setVector(new Vector());
    }

    @Parameterized.Parameters(name = "{index}: " +
            "Current Space: {0}, " +
            "Destination Space: {1}," +
            "Expected Spaces: {2}."
    )
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][]{
            // x = 0, y > 0
            {new Space(A, ONE), new Space(A, TWO), new ArrayList<Space>(){{
                add(new Space(A, TWO));
            }}},
            // x = 0, y > 0 again (with more spaces on the path)
            {new Space(A, ONE), new Space(A, FOUR), new ArrayList<Space>(){{
                add(new Space(A, TWO));
                add(new Space(A, THREE));
                add(new Space(A, FOUR));
            }}},
            // x = 0, y < 0
            {new Space(A, THREE), new Space(A, ONE), new ArrayList<Space>(){{
                add(new Space(A, TWO));
                add(new Space(A, ONE));
            }}},
            // x > 0, y = 0
            {new Space(A, ONE), new Space(C, ONE), new ArrayList<Space>(){{
                add(new Space(B, ONE));
                add(new Space(C, ONE));
            }}},
            // x < 0, y = 0
            {new Space(C, ONE), new Space(A, ONE), new ArrayList<Space>(){{
                add(new Space(B, ONE));
                add(new Space(A, ONE));
            }}},
            // x > 0, y > 0
            {new Space(A, ONE), new Space(C, THREE), new ArrayList<Space>(){{
                add(new Space(B, TWO));
                add(new Space(C, THREE));
            }}},
            // x > 0, y < 0
            {new Space(A, THREE), new Space(C, ONE), new ArrayList<Space>(){{
                add(new Space(B, TWO));
                add(new Space(C, ONE));
            }}},
            // x < 0, y < 0
            {new Space(C, THREE), new Space(A, ONE), new ArrayList<Space>(){{
                add(new Space(B, TWO));
                add(new Space(A, ONE));
            }}},
            // x < 0, y > 0
            {new Space(C, ONE), new Space(A, THREE), new ArrayList<Space>(){{
                add(new Space(B, TWO));
                add(new Space(A, THREE));
            }}},
        });
    }

    @Test
    public void testSetVector() {
        move.getPiece().setVector(move);
        List<Space> actualSpaces = move.getPiece().getVector().getSpaces();
        assertEquals(expectedSpaces.size(), actualSpaces.size());
        for (int i = 0; i < expectedSpaces.size(); i++) {
            assertEquals(expectedSpaces.get(i), actualSpaces.get(i));
        }
    }
}
