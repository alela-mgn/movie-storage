package com.movie.storage.facade;

import com.movie.storage.app.model.FilmBulkResponse;
import com.movie.storage.app.model.FilmCreateRequest;
import com.movie.storage.app.model.FilmResponse;
import com.movie.storage.app.model.MultipleFilmCreateRequest;
import com.movie.storage.mapper.FilmMapper;
import com.movie.storage.model.Film;
import com.movie.storage.service.FilmService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class FilmServiceFacade {
    private final FilmMapper mapper;
    private final FilmService service;

    public FilmResponse createSingleMovie(FilmCreateRequest createRequest) {
        Film film = mapper.toEntity(createRequest);
        Film persistedFilm = service.createFilm(film);
        return mapper.toRestModel(persistedFilm);
    }

    public FilmBulkResponse createMultipleMovie(MultipleFilmCreateRequest createRequest) {
        List<FilmCreateRequest> restModels = createRequest.getFilms();
        List<Film> films = restModels.stream().map(mapper::toEntity).collect(Collectors.toList());

        List<Film> persistedFilms = service.createMultipleFilms(films);
        List<FilmResponse> responses = persistedFilms.stream().map(mapper::toRestModel).collect(Collectors.toList());

        return new FilmBulkResponse().films(responses);
    }

}