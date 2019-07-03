package com.alevel.module.persistence.chessboard.configs;

import com.google.common.collect.ImmutableMap;

import java.util.Map;

import static com.alevel.module.persistence.chessboard.configs.File.A;
import static com.alevel.module.persistence.chessboard.configs.File.B;
import static com.alevel.module.persistence.chessboard.configs.File.C;
import static com.alevel.module.persistence.chessboard.configs.File.D;
import static com.alevel.module.persistence.chessboard.configs.File.E;
import static com.alevel.module.persistence.chessboard.configs.File.F;
import static com.alevel.module.persistence.chessboard.configs.File.G;
import static com.alevel.module.persistence.chessboard.configs.File.H;


public class FileNumericDecoder {

    // TODO consider getting read of it e.g. use enumerator short values
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
