package com.movie.storage.model;

import com.movie.storage.mapper.Default;
import com.movie.storage.service.validation.ValidDate;
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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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

    @NotBlank(message = "Invalid input: mandatory field name is empty")
    @Size(max = 200, message = "Invalid input: Name field length should be in range from 1 to {max} characters")
    @Column(nullable = false, length = 200, name = "name")
    private String name;

    @NotBlank(message = "Invalid input: mandatory field description is empty")
    @Size(max = 1000, message = "Invalid input: Description field length should be in range from 1 to {max} characters")
    @Column(nullable = false, length = 1000, name = "description")
    private String description;

    @NotNull(message = "Invalid input: mandatory field Type is empty")
    @Column(nullable = false, name = "type")
    @Enumerated(EnumType.STRING)
    private FilmType type;

    @NotNull(message = "Invalid input: mandatory field Genre is empty")
    @Column(nullable = false, name = "genre")
    @Enumerated(EnumType.STRING)
    private Genre genre;

    @ValidDate
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
