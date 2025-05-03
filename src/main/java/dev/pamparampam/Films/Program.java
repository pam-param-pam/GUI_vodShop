package dev.pamparampam.Films;

import dev.pamparampam.utils.GENRE;

import java.util.Objects;

public class Program {
    protected String title;
    protected final GENRE genre;

    public Program(String title, GENRE genre) {
        this.title = title;
        this.genre = genre;
    }

    public String getTitle() {
        return title;
    }

    public GENRE getGenre() {
        return genre;
    }

    @Override
    public String toString() {
        return title + " (" + getGenre() + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Program program = (Program) o;
        return Objects.equals(getTitle(), program.getTitle()) && getGenre() == program.getGenre();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), getGenre());
    }
}

