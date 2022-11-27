package com.movie.storage.service.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class DateValidator implements ConstraintValidator<ValidDate, LocalDate> {
    final static LocalDate FIRST_MOVIE_RELEASE_DATE = LocalDate.parse("1885-01-01");

    private static final String DATE_IS_NULL_MESSAGE = "Invalid input: mandatory field ReleaseDate is empty";

    @Override
    public void initialize(ValidDate constraintAnnotation) {
    }

    @Override
    public boolean isValid(LocalDate value, ConstraintValidatorContext context) {
        if (value == null) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(DATE_IS_NULL_MESSAGE)
                    .addConstraintViolation();
            return false;
        }

        LocalDate upLimit = LocalDate.of(LocalDate.now().plus(1, ChronoUnit.YEARS).getYear(), 1, 1);

        return value.isAfter(FIRST_MOVIE_RELEASE_DATE) && value.isBefore(upLimit);
    }
}
