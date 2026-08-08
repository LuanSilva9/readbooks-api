package com.readbooks.api.infrastructure.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.readbooks.api.application.service.BookService;
import com.readbooks.api.infrastructure.controllers.dto.request.CreateBookDto;
import com.readbooks.api.infrastructure.controllers.dto.request.UpdateBookDto;
import com.readbooks.api.infrastructure.controllers.dto.response.ReadBookDto;
import com.readbooks.api.infrastructure.mappers.BookMapper;

import jakarta.validation.Valid;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookService bookService;
    private final BookMapper bookMapper;

    public BookController(BookService bookService, BookMapper bookMapper) {
        this.bookService = bookService;
        this.bookMapper = bookMapper;
    }

    @GetMapping
    public ResponseEntity<List<ReadBookDto>> findAll() {
        List<ReadBookDto> books = bookMapper.entityToDtoList(
            bookService.findAll()
        );

        return ResponseEntity.ok().body(books);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ReadBookDto> findById(@PathVariable UUID id) {
        ReadBookDto book = bookMapper.entityToDto(
            bookService.findById(id)
        );
        
        return ResponseEntity.ok().body(book);
    }
    
    @PostMapping
    public ResponseEntity<ReadBookDto> create(@Valid @RequestBody CreateBookDto dto) {
        ReadBookDto bookCreated = bookMapper.entityToDto(
            bookService.create(dto.toCommand())
        );

        URI location = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(bookCreated.id())
            .toUri();
        
        return ResponseEntity.created(location).body(bookCreated);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ReadBookDto> update(@PathVariable UUID id, @Valid @RequestBody UpdateBookDto dto) {
        ReadBookDto bookUpdated = bookMapper.entityToDto(
            bookService.update(id, dto.toCommand())
        );

        return ResponseEntity.ok().body(bookUpdated);
    }
    
}
