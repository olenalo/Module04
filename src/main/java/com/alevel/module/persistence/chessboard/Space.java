package com.alevel.module.persistence.chessboard;

import com.alevel.module.persistence.chessboard.configs.File;
import com.alevel.module.persistence.chessboard.configs.Rank;

public class Space {

    private File file; // x
    private Rank rank; // y

    public Space(File file, Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    // Ref.: https://stackoverflow.com/a/51014378
    public Space() {
    }

    public File getFile() {
        return file;
    }

    public Rank getRank() {
        return rank;
    }

    @Override
    public String toString() {
        return "Space{" +
                "file=" + file +
                ", rank=" + rank +
                '}';
    }
}
