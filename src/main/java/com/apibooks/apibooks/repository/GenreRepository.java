package com.apibooks.apibooks.repository;

import com.apibooks.apibooks.entities.Genre;
import org.springframework.data.repository.CrudRepository;

public interface GenreRepository extends CrudRepository<Genre,Long> {
}
