package com.movie.storage.logging;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class Pointcuts {

    @Pointcut("execution(* com.movie.storage.controller.FilmController.create(..))")
    public void addFilm() {}

    @Pointcut("execution(* com.movie.storage.controller.FilmController.createFilms(..))")
    public void addFilms() {}

    @Pointcut("execution(* com.movie.storage.controller.FilmController.getByNameFilm(..))")
    public void getFilmByName() {}

    @Pointcut("execution(* com.movie.storage.controller.FilmController.getByDateFilm(..))")
    public void getFilmByDate() {}

    @Pointcut("execution(* com.movie.storage.controller.FilmController.getByTypeFilm(..))")
    public void getFilmByType() {}
}
