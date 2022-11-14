package com.movie.storage.service.impl;

import com.movie.storage.model.Film;
import com.movie.storage.repository.FilmRepository;
import com.movie.storage.service.FilmService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FilmServiceImpl implements FilmService {

    private final FilmRepository filmRepository;
    private final FilmValidator filmValidator;

    @Transactional
    @Override
    public Film createFilm(Film film) {

        filmValidator.validate(film);
        return filmRepository.save(film);
    }

    @Transactional
    @Override
    public List<Film> createMultipleFilms(List<Film> films) {
        films.forEach(filmValidator::validate);

        return filmRepository.saveAll(films);
    }
}
