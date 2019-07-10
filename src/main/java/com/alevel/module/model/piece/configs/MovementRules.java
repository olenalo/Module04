package com.alevel.module.model.piece.configs;

public class MovementRules {
    // TODO populate with loops whenever applicable

    public static final int[][] KING_ALLOWED_MOVEMENT_DELTAS = {
            {0, 1},
            {0, -1},
            {1, 0},
            {-1, 0},
            {1, 1},
            {-1, -1},
            {1, -1},
            {-1, 1}
    };
    public static final int[][] BISHOP_ALLOWED_MOVEMENT_DELTAS = {
            {7, 7},
            {-7, -7},
            {7, -7},
            {-7, 7}
            // TODO add the rest
    };
    public static final int[][] KNIGHT_ALLOWED_MOVEMENT_DELTAS = {
            {-1, -2},
            {-2, 1},
            {-1, 2},
            {1, 2},
            {2, 1},
            {2, -1},
            {1, -2}
    };
    public static final int[][] PAWN_ALLOWED_MOVEMENT_DELTAS = {
            {0, 1},
            {0, 2},
            {1, 1},
            {-1, 1}
    };
    public static final int[][] QUEEN_ALLOWED_MOVEMENT_DELTAS = {
            {0, 7},
            {0, -7},
            {7, 0},
            {-7, 0},
            {7, 7},
            {-7, -7},
            {7, -7},
            {-7, 7},
            {0, 7},
            {0, -7},
            {7, 0},
            {-7, 0},
            {7, 7},
            {-7, -7},
            {7, -7},
            {-4, 4},
            {4, -4},
            {4, 4},
            {0, 4},
            {4, 0},
            // TODO add the rest
    };
    public static final int[][] ROOK_ALLOWED_MOVEMENT_DELTAS = {
            {0, 7},
            {0, -7},
            {7, 0},
            {-7, 0},
            // TODO add the rest
    };
}
