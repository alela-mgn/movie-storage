package com.movie.storage.service.impl;

import com.movie.storage.exception.FilmValidationException;
import com.movie.storage.model.Film;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class FilmValidator {
    private final static LocalDate FIRST_MOVIE_RELEASE_DATE = LocalDate.parse("1885-01-01");
    private final static String INVALID_DATE_ERROR_MESSAGE = "Movie release date should be in range between 1885-01-01 and %s";
    private final static String FIELD_LENGTH_EXCEED_LIMIT_MESSAGE = "Invalid input: '%s' field length should be in range from 1 to %s characters";

    private final static String FIELD_NOT_DEFINED_MESSAGE = "Invalid input: mandatory field '%s' is empty";
    private static final int NAME_MAX_LENGTH = 200;
    private static final int DESCRIPTION_MAX_LENGTH = 1000;


    public void validate(Film film) {
        validateRequiredFields(film);

        if (film.getName() == null || film.getName().trim().length() == 0) {
            throw new FilmValidationException(String.format(FIELD_NOT_DEFINED_MESSAGE, "name"));
        }
        if (film.getDescription() == null || film.getDescription().trim().length() == 0) {
            throw new FilmValidationException(String.format(FIELD_NOT_DEFINED_MESSAGE, "description"));
        }

        validateReleaseDate(film.getReleaseDate());
    }

    private void validateRequiredFields(Film film) {
        if (film.getName().trim().length() > NAME_MAX_LENGTH) {
            throw new FilmValidationException(String.format(FIELD_LENGTH_EXCEED_LIMIT_MESSAGE, "name", NAME_MAX_LENGTH));
        }
        if (film.getDescription().trim().length() > DESCRIPTION_MAX_LENGTH) {
            throw new FilmValidationException(String.format(FIELD_LENGTH_EXCEED_LIMIT_MESSAGE, "description", DESCRIPTION_MAX_LENGTH));
        }
        if (film.getType() == null) {
            throw new FilmValidationException(String.format(FIELD_NOT_DEFINED_MESSAGE, "type"));
        }
        if (film.getGenre() == null) {
            throw new FilmValidationException(String.format(FIELD_NOT_DEFINED_MESSAGE, "genre"));
        }
        if(film.getReleaseDate() == null) {
            throw new FilmValidationException(String.format(FIELD_NOT_DEFINED_MESSAGE, "releaseDate"));
        }
    }


    private void validateReleaseDate(LocalDate localDate) {
        LocalDate upLimit = LocalDate.of(LocalDate.now().getYear(), 12, 31);
        if (!(localDate.isAfter(FIRST_MOVIE_RELEASE_DATE) && localDate.isBefore(LocalDate.now()))) {
            throw new FilmValidationException(String.format(INVALID_DATE_ERROR_MESSAGE, upLimit));
        }
    }
}

