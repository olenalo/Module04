package com.alevel.module.model.chessboard.configs.utils;

import java.util.Map;

public class DecodersLookups {

    // TODO DRY

    /**
     * Look up key by value.
     *
     * Provided single value occurrences.
     *
     * Ref.: https://www.baeldung.com/java-map-key-from-value
     *
     * @param map
     * @param value
     * @param <File>
     * @param <Integer>
     * @return
     */
    public static <File, Integer> File getFileKey(Map<File, Integer> map, Integer value) {
        for (Map.Entry<File, Integer> entry : map.entrySet()) {
            if (entry.getValue().equals(value)) {
                return entry.getKey();
            }
        }
        return null;
    }

    public static <Rank, Integer> Rank getRankKey(Map<Rank, Integer> map, Integer value) {
        for (Map.Entry<Rank, Integer> entry : map.entrySet()) {
            if (entry.getValue().equals(value)) {
                return entry.getKey();
            }
        }
        return null;
    }
}
