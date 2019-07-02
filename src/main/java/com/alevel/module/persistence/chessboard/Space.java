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

    public File getFile() {
        return file;
    }

    public Rank getRank() {
        return rank;
    }
}
