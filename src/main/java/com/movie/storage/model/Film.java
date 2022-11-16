package com.movie.storage.model;

import com.movie.storage.mapper.Default;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
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

    @Column(nullable = false, name = "type")
    @Enumerated(EnumType.STRING)
    private FilmType type;

    @Column(nullable = false, name = "genre")
    @Enumerated(EnumType.STRING)
    private Genre genre;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    @Default
    public Film(Long id, String name, String description, FilmType type, Genre genre, LocalDate releaseDate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.type = type;
        this.genre = genre;
        this.releaseDate = releaseDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Film film = (Film) o;
        return Objects.equals(name, film.name) && Objects.equals(description, film.description) && type == film.type && genre == film.genre && Objects.equals(releaseDate, film.releaseDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, type, genre, releaseDate);
    }

    @Override
    public String toString() {
        return "Film{" +
                "id=" + id +
                ",name='" + name + "'," +
                "releaseDate=" + releaseDate +
                '}';
    }
}
