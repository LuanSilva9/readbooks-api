package com.readbooks.api.domain.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.readbooks.api.domain.model.Book;
import com.readbooks.api.domain.model.SummaryDaily;

public interface SummaryDailyRepository extends JpaRepository<SummaryDaily, UUID>{
    Optional<SummaryDaily> findByBookIdAndCreatedAtBetween(UUID bookId, LocalDateTime startDay, LocalDateTime endDay);


    default Optional<SummaryDaily> findTodayByBook(Book book) {
        LocalDate today = LocalDate.now();
        LocalDateTime startOfDay = today.atStartOfDay();
        LocalDateTime endOfDay = today.plusDays(1).atStartOfDay();

        return findByBookIdAndCreatedAtBetween(book.getId(), startOfDay, endOfDay);
    }
}
