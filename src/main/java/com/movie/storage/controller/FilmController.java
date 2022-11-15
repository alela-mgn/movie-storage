package com.movie.storage.controller;

import com.movie.storage.app.model.FilmBulkResponse;
import com.movie.storage.app.model.FilmCreateRequest;
import com.movie.storage.app.model.FilmResponse;
import com.movie.storage.app.model.FilmType;
import com.movie.storage.app.model.MultipleFilmCreateRequest;
import com.movie.storage.facade.FilmServiceFacade;
import com.movie.storage.model.Film;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/movie-api/film")
@RequiredArgsConstructor
public class FilmController {

    private final FilmServiceFacade serviceFacade;

    @PostMapping("/single")
    public FilmResponse create(@RequestBody FilmCreateRequest request) {
        return serviceFacade.createSingleMovie(request);
    }

    @PostMapping("/multiple")
    public FilmBulkResponse createFilms(@RequestBody MultipleFilmCreateRequest request) {
        return serviceFacade.createMultipleMovie(request);
    }

    @GetMapping("/search/name/{filmName}")
    public List<Film> getByNameFilm(@PathVariable String filmName) {
        return serviceFacade.getByName(filmName);
    }

    @GetMapping("/search/type/{filmType}")
    public List<Film> getByTypeFilm(@PathVariable FilmType filmType) {
        return serviceFacade.getByType(filmType);
    }

    @GetMapping("/search/date/{releaseDate}")
    public List<Film> getByDateFilm(@PathVariable
                                    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate releaseDate) {
        return serviceFacade.getByReleaseDate(releaseDate);
    }
}
