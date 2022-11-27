package com.movie.storage.controller;

import com.movie.storage.app.model.FilmBulkResponse;
import com.movie.storage.app.model.FilmCreateRequest;
import com.movie.storage.app.model.FilmResponse;
import com.movie.storage.app.model.FilmType;
import com.movie.storage.app.model.MultipleFilmCreateRequest;
import com.movie.storage.facade.FilmServiceFacade;
import com.movie.storage.model.Film;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class FilmController {
    private static final int DEFAULT_PAGE_SIZE = 5;

    private final FilmServiceFacade serviceFacade;

    @PostMapping("/single")
    public FilmResponse create(@RequestBody FilmCreateRequest request) {
        log.info("Create single film. Create request ={}", request);

        FilmResponse result = serviceFacade.createSingleMovie(request);

        log.info("Create single film. Finished successfully");
        return result;
    }

    @PostMapping("/multiple")
    public FilmBulkResponse createFilms(@RequestBody MultipleFilmCreateRequest request) {
        log.info("Create multiple films. Create request ={}", request);

        FilmBulkResponse result = serviceFacade.createMultipleMovie(request);

        log.info("Create multiple films. Finished successfully");
        return result;
    }

    @GetMapping("/search/name/{filmName}")
    public List<Film> getByNameFilm(@PathVariable String filmName,
                                    @PageableDefault(size = DEFAULT_PAGE_SIZE, sort = {"id"}, direction = Sort.Direction.ASC) Pageable pageable) {
        log.info("Get film by name ={}", filmName);

        List<Film> result = serviceFacade.getByName(filmName, pageable);

        log.info("Get film successfully = {}", result);
        return result;
    }

    @GetMapping("/search/type/{filmType}")
    public List<Film> getByTypeFilm(@PathVariable FilmType filmType,
                                    @PageableDefault(size = DEFAULT_PAGE_SIZE, sort = {"id"}, direction = Sort.Direction.ASC) Pageable pageable) {
        log.info("Get film by type ={}", filmType);

        List<Film> result = serviceFacade.getByType(filmType, pageable);

        log.info("Get film successfully = {}", result);
        return result;
    }

    @GetMapping("/search/date/{releaseDate}")
    public List<Film> getByDateFilm(@PathVariable
                                    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate releaseDate,
                                    @PageableDefault(size = DEFAULT_PAGE_SIZE, sort = {"id"}, direction = Sort.Direction.ASC) Pageable pageable) {
        log.info("Get film by date ={}", releaseDate);

        List<Film> result = serviceFacade.getByReleaseDate(releaseDate, pageable);

        log.info("Get film successfully = {}", result);
        return result;
    }
}
