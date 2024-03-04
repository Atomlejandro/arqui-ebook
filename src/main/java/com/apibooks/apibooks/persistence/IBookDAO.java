package com.apibooks.apibooks.persistence;

import com.apibooks.apibooks.entities.Book;

import java.util.List;
import java.util.Optional;

public interface IBookDAO {
    List<Book> findAll();
    Optional<Book> findById(Long idBook);


    Optional<Book> findByIsbn(String isbn);
    List<Book> findByPriceInRange(double minPrice, double maxPrice);

    List<Book> findByTitle(String title);

    void save(Book book);


    void deleteById(Long idBook);
}
