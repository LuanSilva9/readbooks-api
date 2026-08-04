package com.readbooks.api.model;

import java.time.LocalDate;
import java.util.UUID;

import com.readbooks.api.errors.exception.BusinessException;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "books")
@NoArgsConstructor
@EqualsAndHashCode(of="id")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "book_name", unique = true, nullable = false)
    private String bookName;

    @Column(name = "description")
    private String description;

    @Column(name = "link")
    private String link;

    @Column(name = "start_read_date")
    private LocalDate startReadDate;

    @Column(name = "end_read_date")
    private LocalDate endReadDate;
    
    @Column(name = "num_pages_read")
    private Integer numPagesRead;

    @Column(name = "num_pages_full", nullable = false)
    private Integer numPagesFull;

    public Book(String bookName, String description, String link, LocalDate startReadDate, LocalDate endReadDate, Integer numPagesRead, Integer numPagesFull) {
        Integer safePagesRead = numPagesRead == null ? 0 : numPagesRead;

        validatePageNumbers(safePagesRead, numPagesFull);
        validateDates(startReadDate, endReadDate);

        this.bookName = bookName;
        this.description = description;
        this.link = link;
        this.startReadDate = startReadDate;
        this.endReadDate = endReadDate;
        this.numPagesRead = safePagesRead;
        this.numPagesFull = numPagesFull;
    }

    private void validatePageNumbers(Integer numPagesRead, Integer numPagesFull) {
        if (numPagesFull == null || numPagesFull <= 0) {
            throw new BusinessException("O total de páginas do livro deve ser maior que zero.");
        }

        if (numPagesRead < 0) {
            throw new BusinessException("O número de páginas lidas não pode ser negativo.");
        }

        if (numPagesRead > numPagesFull) {
            throw new BusinessException("Número de páginas lidas ultrapassa tamanho físico do livro.");
        }
    }

    private void validateDates(LocalDate start, LocalDate end) {
        if (start != null && end != null && end.isBefore(start)) {
            throw new BusinessException("A data final de leitura não pode ser anterior à data inicial.");
        }
    }
}
