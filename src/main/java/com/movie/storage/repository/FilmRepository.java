package com.movie.storage.repository;

import com.movie.storage.model.Film;
import com.movie.storage.model.FilmType;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FilmRepository extends JpaRepository<Film, Long> {

    List<Film> findByName(String filmName, Pageable pageable);

    List<Film> findByType(FilmType type, Pageable pageable);

    List<Film> findByReleaseDate(LocalDate localDate, Pageable pageable);

}
