package com.readbooks.api.infrastructure.mappers;

import java.util.List;

import org.springframework.stereotype.Component;

import com.readbooks.api.domain.model.Book;
import com.readbooks.api.infrastructure.controllers.dto.response.ReadBookDto;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class BookMapper {
    private final SummaryDailyMapper summaryDailyMapper;

    public ReadBookDto entityToDto(Book entity) {
        return new ReadBookDto(
            entity.getId(),
            entity.getBookName(),
            entity.getDescription(),
            entity.getLink(),
            entity.getStartReadDate(),
            entity.getEndReadDate(),
            entity.getNumPagesRead(),
            entity.getNumPagesFull(),
            summaryDailyMapper.entityToDtoList(entity.getSummaryDailies())
        );
    }

    public List<ReadBookDto> entityToDtoList(List<Book> entities) {
        return entities.stream().map(this::entityToDto).toList();
    }
}
