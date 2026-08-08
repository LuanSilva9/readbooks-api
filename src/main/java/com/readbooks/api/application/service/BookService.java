package com.readbooks.api.application.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.readbooks.api.application.command.CreateBookCommand;
import com.readbooks.api.application.command.UpdateBookCommand;
import com.readbooks.api.application.exceptions.NotFoundException;
import com.readbooks.api.domain.model.Book;
import com.readbooks.api.domain.repository.BookRepository;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Transactional
    public Book create(CreateBookCommand commandBook) {
        Book newBook = new Book(
            commandBook.bookName(),
            commandBook.description(),
            commandBook.link(),
            commandBook.numPagesRead(),
            commandBook.numPagesFull()
        );

        return this.bookRepository.save(newBook);
    }

    @Transactional
    public Book update(UUID id, UpdateBookCommand commandBook) {
        Book bookFound = this.findById(id);

        bookFound.update(
            commandBook.bookName(),
            commandBook.description(),
            commandBook.link(),
            commandBook.numPagesFull()
        );

        return bookFound;
    }

    @Transactional(readOnly = true)
    public Book findById(UUID id) {
        Book bookFound = this.bookRepository.findById(id).orElseThrow(() -> new NotFoundException("Livro"));
        
        return bookFound;
    }

    @Transactional(readOnly = true)
    public List<Book> findAll() {
        List<Book> booksFounded = this.bookRepository.findAll();
        
        return booksFounded;
    }


}
