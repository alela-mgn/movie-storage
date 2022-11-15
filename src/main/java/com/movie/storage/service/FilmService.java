package com.movie.storage.service;

import com.movie.storage.model.Film;
import com.movie.storage.model.FilmType;

import java.time.LocalDate;
import java.util.List;

public interface FilmService {

    Film createFilm(Film film);

    List<Film> createMultipleFilms(List<Film> films);

    List<Film> getByNameFilm(String nameFilm);

    List<Film> getByFilmType(FilmType filmType);

    List<Film> getByReleaseDateFilm(LocalDate releaseDate);
}
