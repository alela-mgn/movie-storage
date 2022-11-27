package com.movie.storage.service.impl;

import com.movie.storage.model.Film;
import com.movie.storage.model.FilmType;
import com.movie.storage.repository.FilmRepository;
import com.movie.storage.service.FilmService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Slf4j
public class FilmServiceImpl implements FilmService {
    private final FilmRepository filmRepository;
    private final FilmValidator filmValidator;

    @Transactional
    @Override
    public Film createFilm(Film film) {
        log.debug("Start Create single Film");

        filmValidator.validate(film);
        Film savedFilm = filmRepository.save(film);
        log.debug("Film {} was successfully saved to storage", savedFilm);

        return savedFilm;
    }

    @Transactional
    @Override
    public List<Film> createMultipleFilms(List<Film> films) {
        log.info("Start Create {} films", films.size());

        films.forEach(filmValidator::validate);
        List<Film> savedFilms = filmRepository.saveAll(films);

        log.info("{} Films [{}] were successfully saved to storage", savedFilms.size(),
                savedFilms.stream().map(Film::toString).collect(Collectors.joining(",")));
        return savedFilms;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Film> getByName(String name, Pageable pageable) {
        List<Film> films = filmRepository.findByName(name, pageable);

        log.info("{} films were found by name={}", films.size(), name);
        return films;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Film> getByFilmType(FilmType filmType, Pageable pageable) {
        List<Film> films = filmRepository.findByType(filmType, pageable);

        log.info("{} films were found by film type={}", films.size(), filmType);
        return films;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Film> getByReleaseDate(LocalDate releaseDate, Pageable pageable) {
        List<Film> films = filmRepository.findByReleaseDate(releaseDate, pageable);

        log.info("{} films were found by release date={}", films.size(), releaseDate);
        return films;
    }
}
