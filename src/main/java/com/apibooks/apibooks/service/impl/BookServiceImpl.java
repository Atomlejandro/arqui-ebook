package com.apibooks.apibooks.service.impl;

import com.apibooks.apibooks.entities.Book;
import com.apibooks.apibooks.persistence.IBookDAO;
import com.apibooks.apibooks.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements IBookService {

    @Autowired
    private IBookDAO bookDAO;
    @Override
    public List<Book> findAll() {
        return bookDAO.findAll();
    }

    @Override
    public Optional<Book> findById(Long idBook) {
        return bookDAO.findById(idBook);
    }

    @Override
    public Optional<Book> findByIsbn(String isbn) {
        return bookDAO.findByIsbn(isbn);
    }

    @Override
    public List<Book> findByPriceInRange(double minPrice, double maxPrice) {
        return bookDAO.findByPriceInRange(minPrice, maxPrice);
    }

    @Override
    public List<Book> findByTitle(String title) {
        return bookDAO.findByTitle(title);
    }

    @Override
    public Book save(Book book) {
    bookDAO.save(book);
        return book;
    }



    @Override
    public Optional<Book> updateBook(Long id, Book bookDetails) {
        Optional<Book> bookOptional = bookDAO.findById(id);

        if (bookOptional.isPresent()) {
            Book existingBook = bookOptional.get();
            // Actualiza las propiedades del libro existente con bookDetails
            existingBook.setTitle(bookDetails.getTitle());
            existingBook.setIsbn(bookDetails.getIsbn());
            existingBook.setPrice(bookDetails.getPrice());
            existingBook.setUnits(bookDetails.getUnits());
            existingBook.setImageUrl(bookDetails.getImageUrl());
            existingBook.setGenres(bookDetails.getGenres()); // Asegúrate de manejar correctamente las relaciones

            // Guarda y devuelve el libro actualizado
            bookDAO.save(existingBook);
            return Optional.of(existingBook);
        }

        return Optional.empty(); // Devuelve un Optional vacío si el libro no se encuentra
    }
}
