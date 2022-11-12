package com.movie.storage.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "film")
@Getter
@NoArgsConstructor
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(nullable = false, length = 200, name = "name")
    private String name;

    @Column(nullable = false, length = 1000, name = "description")
    private String description;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private TypeFilm type;

    @Column(name = "genre")
    @Enumerated(EnumType.STRING)
    private Genre genre;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    private Film(String name, TypeFilm type, Genre genre, LocalDate releaseDate, String description) {
        this.name = name;
        this.type = type;
        this.genre = genre;
        this.releaseDate = releaseDate;
        this.description = description;
    }

    public static Film of(String name, TypeFilm type, Genre genre, LocalDate date, String description) {
        return new Film(name, type, genre, date, description);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Film film = (Film) o;
        return Objects.equals(name, film.name) && Objects.equals(type, film.type) && Objects.equals(genre, film.genre) && Objects.equals(releaseDate, film.releaseDate) && Objects.equals(description, film.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, type, genre, releaseDate, description);
    }
}
