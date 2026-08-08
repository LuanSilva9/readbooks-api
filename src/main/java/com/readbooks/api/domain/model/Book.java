package com.readbooks.api.domain.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import com.readbooks.api.application.exceptions.BusinessException;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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

    @OneToMany(mappedBy = "book", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<SummaryDaily> summaryDailies = new ArrayList<>();

    public Book(String bookName, String description, String link, Integer numPagesRead, Integer numPagesFull) {
        Integer safePagesRead = Objects.requireNonNullElse(numPagesRead, 0);

        validatePageNumbers(safePagesRead, numPagesFull);

        this.bookName = bookName;
        this.description = description;
        this.link = link;
        this.startReadDate = LocalDate.now();
        this.endReadDate = null;
        this.numPagesRead = safePagesRead;
        this.numPagesFull = numPagesFull;

        validateAndSetEndDateRead();
    }

    public void update(String bookName, String description, String link, Integer numPagesFull) {
        Integer safePagesFull = Objects.requireNonNullElse(numPagesFull, this.numPagesFull);

        validatePageNumbers(this.numPagesRead, safePagesFull);

        if(bookName != null && !bookName.isBlank()) this.bookName = bookName;
        if(description != null && !description.isBlank()) this.description = description;
        if(link != null && !link.isBlank()) this.link = link;
        
        this.numPagesFull = safePagesFull; 

        validateAndSetEndDateRead();
    }

    public void newProgressBook(Integer pagesRead) {
        Integer safePagesRead = Objects.requireNonNullElse(pagesRead, 0);
        Integer newTotalRead = this.numPagesRead + safePagesRead;

        validatePageNumbers(newTotalRead, this.numPagesFull);

        this.numPagesRead = newTotalRead;

        validateAndSetEndDateRead();
    }

    private void validateAndSetEndDateRead() {
        if (this.endReadDate == null && Objects.equals(this.numPagesRead, this.numPagesFull)) {
            this.endReadDate = LocalDate.now();
        }
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

    /* --- Getters ---- */

    public UUID getId() {
        return id;
    }

    public String getBookName() {
        return bookName;
    }

    public String getDescription() {
        return description;
    }

    public String getLink() {
        return link;
    }

    public LocalDate getStartReadDate() {
        return startReadDate;
    }

    public LocalDate getEndReadDate() {
        return endReadDate;
    }

    public Integer getNumPagesRead() {
        return numPagesRead;
    }

    public Integer getNumPagesFull() {
        return numPagesFull;
    }

    public List<SummaryDaily> getSummaryDailies() {
        return Collections.unmodifiableList(summaryDailies);
    }
}
