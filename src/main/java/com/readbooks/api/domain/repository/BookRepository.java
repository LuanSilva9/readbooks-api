package com.readbooks.api.domain.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.readbooks.api.domain.model.Book;

public interface BookRepository extends JpaRepository<Book, UUID> {
    
}