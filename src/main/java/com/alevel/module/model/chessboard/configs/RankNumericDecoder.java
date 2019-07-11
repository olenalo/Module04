package com.alevel.module.model.chessboard.configs;

import com.google.common.collect.ImmutableMap;

import java.util.Map;

import static com.alevel.module.model.chessboard.configs.Rank.ONE;
import static com.alevel.module.model.chessboard.configs.Rank.TWO;
import static com.alevel.module.model.chessboard.configs.Rank.THREE;
import static com.alevel.module.model.chessboard.configs.Rank.FOUR;
import static com.alevel.module.model.chessboard.configs.Rank.FIVE;
import static com.alevel.module.model.chessboard.configs.Rank.SIX;
import static com.alevel.module.model.chessboard.configs.Rank.SEVEN;
import static com.alevel.module.model.chessboard.configs.Rank.EIGHT;

public class RankNumericDecoder {

    public static Map<Rank, Integer> RANK_NUMERIC_DECODER = ImmutableMap.<Rank, Integer>builder()
            .put(ONE, 1)
            .put(TWO, 2)
            .put(THREE, 3)
            .put(FOUR, 4)
            .put(FIVE, 5)
            .put(SIX, 6)
            .put(SEVEN, 7)
            .put(EIGHT, 8)
            .build();
}
