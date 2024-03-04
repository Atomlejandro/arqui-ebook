package com.apibooks.apibooks.repository;

import com.apibooks.apibooks.entities.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends CrudRepository<Book, Long> {

    List<Book> findBookByPriceBetween(double minPrice, double maxPrice);
    List<Book> findByTitleContainingIgnoreCase(String title);

    Optional<Book> findByIsbn(String isbn);
}
