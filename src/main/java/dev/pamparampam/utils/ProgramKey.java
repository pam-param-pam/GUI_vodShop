package dev.pamparampam.utils;

import java.util.Objects;

public class ProgramKey {
    private GENRE genre;
    private final String title;

    public ProgramKey(GENRE type, String title) {
        this.genre = type;
        this.title = title;
    }

    public GENRE getGenre() {
        return genre;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProgramKey that)) return false;
        return genre == that.genre && Objects.equals(title, that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(genre, title);
    }

    @Override
    public String toString() {
        return genre + " - " + title;
    }
}