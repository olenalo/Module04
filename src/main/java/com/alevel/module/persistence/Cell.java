package com.alevel.module.persistence;

import com.alevel.module.persistence.configs.File;
import com.alevel.module.persistence.configs.Rank;

public class Cell {

    private File file;
    private Rank rank;
    private boolean isEmpty; // won't be stored in a db

    public Cell(File file, Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public File getFile() {
        return file;
    }

    public Rank getRank() {
        return rank;
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public void setEmpty(boolean empty) {
        isEmpty = empty;
    }
}
