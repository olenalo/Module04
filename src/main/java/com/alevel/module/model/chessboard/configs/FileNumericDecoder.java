package com.alevel.module.model.chessboard.configs;

import com.google.common.collect.ImmutableMap;

import java.util.Map;

import static com.alevel.module.model.chessboard.configs.File.A;
import static com.alevel.module.model.chessboard.configs.File.B;
import static com.alevel.module.model.chessboard.configs.File.C;
import static com.alevel.module.model.chessboard.configs.File.D;
import static com.alevel.module.model.chessboard.configs.File.E;
import static com.alevel.module.model.chessboard.configs.File.F;
import static com.alevel.module.model.chessboard.configs.File.G;
import static com.alevel.module.model.chessboard.configs.File.H;

/**
 * Decode space file type to numeric value.
 */
public class FileNumericDecoder {

    public static Map<File, Integer> FILE_NUMERIC_DECODER = ImmutableMap.<File, Integer>builder()
            .put(A, 1)
            .put(B, 2)
            .put(C, 3)
            .put(D, 4)
            .put(E, 5)
            .put(F, 6)
            .put(G, 7)
            .put(H, 8)
            .build();
}
