package com.alevel.module.model.piece.configs.rules;

public class MovementRules {
    // TODO consider populating with loops
    // TODO review the rules again! test against much more examples!

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
            {-7, 7},

            {6, 6},
            {-6, -6},
            {6, -6},
            {-6, 6},

            {5, 5},
            {-5, -5},
            {5, -5},
            {-5, 5},

            {4, 4},
            {-4, -4},
            {4, -4},
            {-4, 4},

            {3, 3},
            {-3, -3},
            {3, -3},
            {-3, 3},

            {2, 2},
            {-2, -2},
            {2, -2},
            {-2, 2},

            {1, 1},
            {-1, -1},
            {1, -1},
            {-1, 1},
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
            {0, 1}, // TODO {0, -1} for black (forward only)
            {1, 1},  // TODO diagonally - only to capture!
            {-1, 1},
            // TODO other cases?
            {0, 2}, // TODO move to specific rules; if first move only! White only (forward).
            {0, -2}, // TODO move to specific rules; if first move only! Black only (forward).
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

            {0, 4},
            {0, -4},
            {4, 0},
            {-4, 0},
            {4, 4},
            {-4, -4},
            {4, -4},
            {-4, 4},

            {0, 2},
            {0, -2},
            {2, 0},
            {-2, 0},
            {2, 2},
            {-2, -2},
            {2, -2},
            {-2, 2},

            {0, 6},
            {0, -6},
            {6, 0},
            {-6, 0},
            {6, 6},
            {-6, -6},
            {6, -6},
            {-6, 6},

            {0, 5},
            {0, -5},
            {5, 0},
            {-5, 0},
            {5, 5},
            {-5, -5},
            {5, -5},
            {-5, 5},

            {0, 3},
            {0, -3},
            {3, 0},
            {-3, 0},
            {3, 3},
            {-3, -3},
            {3, -3},
            {-3, 3},

            {0, 1},
            {0, -1},
            {1, 0},
            {-1, 0},
            {1, 1},
            {-1, -1},
            {1, -1},
            {-1, 1},
    };
    public static final int[][] ROOK_ALLOWED_MOVEMENT_DELTAS = {
            {0, 7},
            {0, -7},
            {7, 0},
            {-7, 0},

            {0, 6},
            {0, -6},
            {6, 0},
            {-6, 0},

            {0, 5},
            {0, -5},
            {5, 0},
            {-5, 0},

            {0, 4},
            {0, -4},
            {4, 0},
            {-4, 0},

            {0, 3},
            {0, -3},
            {3, 0},
            {-3, 0},

            {0, 2},
            {0, -2},
            {2, 0},
            {-2, 0},

            {0, 1},
            {0, -1},
            {1, 0},
            {-1, 0},
    };
}
