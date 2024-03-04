package com.apibooks.apibooks.service;

import com.apibooks.apibooks.entities.Book;

import java.util.List;
import java.util.Optional;

public interface IBookService {
    List<Book> findAll();
    Optional<Book> findById(Long idBook);


    Optional<Book> findByIsbn(String isbn);
    List<Book> findByPriceInRange(double minPrice, double maxPrice);

    List<Book> findByTitle(String title);

    Book save(Book book);




    Optional<Book> updateBook(Long id, Book bookDetails);
}
