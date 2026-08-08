package com.readbooks.api.infrastructure.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.readbooks.api.application.usecases.ProgressBookUseCase;
import com.readbooks.api.infrastructure.controllers.dto.request.NewProgressBookDto;
import com.readbooks.api.infrastructure.controllers.dto.response.ReadBookDto;
import com.readbooks.api.infrastructure.mappers.BookMapper;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/api/book-progress")
public class ProgressBookController {
    private final ProgressBookUseCase bookUseCase;
    private final BookMapper bookMapper;

    public ProgressBookController(ProgressBookUseCase bookUseCase, BookMapper bookMapper) {
        this.bookUseCase = bookUseCase;
        this.bookMapper = bookMapper;
    }

    @PutMapping("/new/{id}")
    public ResponseEntity<ReadBookDto> addProgress(@PathVariable UUID id, @Valid @RequestBody NewProgressBookDto dto) {
        ReadBookDto bookUpdated = bookMapper.entityToDto(
            bookUseCase.execute(id, dto.toCommand())
        );
        
        return ResponseEntity.ok().body(bookUpdated);
    }
}
