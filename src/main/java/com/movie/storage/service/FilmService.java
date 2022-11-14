package com.movie.storage.service;

import com.movie.storage.model.Film;

import java.util.List;

public interface FilmService {

    Film createFilm(Film film);

    List<Film> createMultipleFilms(List<Film> films);
}
