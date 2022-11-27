package com.movie.storage.service;

import com.movie.storage.model.Film;
import com.movie.storage.model.FilmType;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface FilmService {

    Film createFilm(Film film);

    List<Film> createMultipleFilms(List<Film> films);

    List<Film> getByName(String nameFilm, Pageable pageable);

    List<Film> getByFilmType(FilmType filmType, Pageable pageable);

    List<Film> getByReleaseDate(LocalDate releaseDate, Pageable pageable);
}
