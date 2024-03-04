package com.apibooks.apibooks.persistence.impl;

import com.apibooks.apibooks.entities.Book;
import com.apibooks.apibooks.persistence.IBookDAO;
import com.apibooks.apibooks.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
@Component
public class BookDAOImpl implements IBookDAO {

    private final BookRepository bookRepository;
    @Autowired
    public BookDAOImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> findAll() {
        return (List<Book>) bookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(Long idBook) {
        return bookRepository.findById(idBook);
    }

    @Override
    public Optional<Book> findByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn);
    }

    @Override
    public List<Book> findByPriceInRange(double minPrice, double maxPrice) {
        return bookRepository.findBookByPriceBetween(minPrice,maxPrice);
    }

    @Override
    public List<Book> findByTitle(String title) {
        return bookRepository.findByTitleContainingIgnoreCase(title);
    }

    @Override
    public void save(Book book) {
        bookRepository.save(book);
    }

    @Override
    public void deleteById(Long idBook) {
        bookRepository.deleteById(idBook);
    }
}
