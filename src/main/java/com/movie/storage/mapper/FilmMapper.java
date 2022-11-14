package com.movie.storage.mapper;

import com.movie.storage.app.model.FilmCreateRequest;
import com.movie.storage.app.model.FilmResponse;
import com.movie.storage.model.Film;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FilmMapper {

    Film toEntity(FilmCreateRequest filmCreateRequest);

    FilmResponse toRestModel(Film film);
}
