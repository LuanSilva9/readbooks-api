package com.readbooks.api.application.usecases;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.readbooks.api.application.command.NewProgressBookCommand;
import com.readbooks.api.application.exceptions.NotFoundException;
import com.readbooks.api.domain.model.Book;
import com.readbooks.api.domain.model.SummaryDaily;
import com.readbooks.api.domain.repository.BookRepository;
import com.readbooks.api.domain.repository.SummaryDailyRepository;

@Service
public class ProgressBookUseCase {
    private final BookRepository bookRepository;
    private final SummaryDailyRepository summaryDailyRepository;

    public ProgressBookUseCase(BookRepository bookRepository, SummaryDailyRepository summaryDailyRepository) {
        this.bookRepository = bookRepository;
        this.summaryDailyRepository = summaryDailyRepository;
    }

    @Transactional
    public Book execute(UUID id, NewProgressBookCommand command) {
        Book bookFound = this.bookRepository.findById(id).orElseThrow(() -> new NotFoundException("Livro"));
        
        bookFound.newProgressBook(command.pagesRead());

        Optional<SummaryDaily> summaryDailyFound = this.summaryDailyRepository.findTodayByBook(bookFound);

        if(summaryDailyFound.isPresent()) {
            SummaryDaily summaryDaily = summaryDailyFound.get();
            summaryDaily.updateSummaryContent(command.dailySummaryContent());
        } else {
            SummaryDaily newSummaryDaily = new SummaryDaily(command.dailySummaryContent(), bookFound);

            this.summaryDailyRepository.save(newSummaryDaily);
        }


        return bookFound;
    }
    
}
