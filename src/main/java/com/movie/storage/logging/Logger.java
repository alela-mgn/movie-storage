package com.movie.storage.logging;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class Logger {

    @Around("Pointcuts.addFilm()")
    public Object aroundAddFilm(ProceedingJoinPoint joinPoint) throws Throwable {
        return logOperation("Create single film", joinPoint);
    }

    @Around("Pointcuts.addFilms()")
    public Object aroundAddFilms(ProceedingJoinPoint joinPoint) throws Throwable {
        return logOperation("Create multiple films", joinPoint);
    }

    @Around("Pointcuts.getFilmByName()")
    public Object aroundGetFilmByName(ProceedingJoinPoint joinPoint) throws Throwable {
        return logOperation("Get film by name", joinPoint);
    }

    @Around("Pointcuts.getFilmByType()")
    public Object aroundGetFilmByType(ProceedingJoinPoint joinPoint) throws Throwable {
        return logOperation("Get film by type", joinPoint);
    }

    @Around("Pointcuts.getFilmByDate()")
    public Object aroundGetFilmByDate(ProceedingJoinPoint joinPoint) throws Throwable {
        return logOperation("Get film by date", joinPoint);
    }

    private Object logOperation(String operationName, ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] methodArgs = joinPoint.getArgs();
        log.info("{} = {}", operationName, methodArgs);

        Object result = joinPoint.proceed();

        log.info("{}. Finished successfully = {}", operationName, result);
        return result;
    }
}
