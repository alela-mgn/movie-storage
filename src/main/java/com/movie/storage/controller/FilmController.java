package com.movie.storage.controller;

import com.movie.storage.app.model.FilmBulkResponse;
import com.movie.storage.app.model.FilmCreateRequest;
import com.movie.storage.app.model.FilmResponse;
import com.movie.storage.app.model.FilmType;
import com.movie.storage.app.model.MultipleFilmCreateRequest;
import com.movie.storage.facade.FilmServiceFacade;
import com.movie.storage.model.Film;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
    private static final int DEFAULT_PAGE_SIZE = 5;

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
    public List<Film> getByNameFilm(@PathVariable String filmName,
                                    @PageableDefault(size = DEFAULT_PAGE_SIZE, sort = {"id"}, direction = Sort.Direction.ASC) Pageable pageable) {
        return serviceFacade.getByName(filmName, pageable);
    }

    @GetMapping("/search/type/{filmType}")
    public List<Film> getByTypeFilm(@PathVariable FilmType filmType,
                                    @PageableDefault(size = DEFAULT_PAGE_SIZE, sort = {"id"}, direction = Sort.Direction.ASC) Pageable pageable) {
        return serviceFacade.getByType(filmType, pageable);
    }

    @GetMapping("/search/date/{releaseDate}")
    public List<Film> getByDateFilm(@PathVariable
                                    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate releaseDate,
                                    @PageableDefault(size = DEFAULT_PAGE_SIZE, sort = {"id"}, direction = Sort.Direction.ASC) Pageable pageable) {
        return serviceFacade.getByReleaseDate(releaseDate, pageable);
    }
}
