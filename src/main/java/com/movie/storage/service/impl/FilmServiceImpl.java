package com.movie.storage.service.impl;

import com.movie.storage.model.Film;
import com.movie.storage.model.FilmType;
import com.movie.storage.repository.FilmRepository;
import com.movie.storage.service.FilmService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
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

    @Transactional(readOnly = true)
    @Override
    public List<Film> getByName(String name, Pageable pageable) {
        return filmRepository.findByName(name, pageable);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Film> getByFilmType(FilmType filmType, Pageable pageable) {
        return filmRepository.findByType(filmType, pageable);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Film> getByReleaseDate(LocalDate releaseDate, Pageable pageable) {
        return filmRepository.findByReleaseDate(releaseDate, pageable);
    }
}
