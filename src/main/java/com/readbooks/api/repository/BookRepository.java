package com.readbooks.api.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.readbooks.api.model.Book;

public interface BookRepository extends JpaRepository<Book, UUID> {
    
}
