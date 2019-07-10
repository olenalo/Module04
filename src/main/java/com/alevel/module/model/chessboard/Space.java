package com.alevel.module.model.chessboard;

import com.alevel.module.model.chessboard.configs.File;
import com.alevel.module.model.chessboard.configs.Rank;

import java.util.Objects;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Space space = (Space) o;
        return file == space.file &&
                rank == space.rank;
    }

    @Override
    public int hashCode() {
        return Objects.hash(file, rank);
    }

    @Override
    public String toString() {
        return "Space{" +
                "file=" + file +
                ", rank=" + rank +
                '}';
    }
}
