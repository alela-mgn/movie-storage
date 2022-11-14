package com.movie.storage.controller;

import com.movie.storage.app.model.FilmBulkResponse;
import com.movie.storage.app.model.FilmCreateRequest;
import com.movie.storage.app.model.FilmResponse;
import com.movie.storage.app.model.MultipleFilmCreateRequest;
import com.movie.storage.facade.FilmServiceFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movie-api/")
@RequiredArgsConstructor
public class FilmController {

    private final FilmServiceFacade serviceFacade;

    @PostMapping("film/single")
    public FilmResponse create(@RequestBody FilmCreateRequest request) {
        return serviceFacade.createSingleMovie(request);
    }

    @PostMapping("film/multiple")
    public FilmBulkResponse createFilms(@RequestBody MultipleFilmCreateRequest request) {
        return serviceFacade.createMultipleMovie(request);
    }
}
