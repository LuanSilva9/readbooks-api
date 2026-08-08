package com.readbooks.api.infrastructure.controllers.dto.request;

import com.readbooks.api.application.command.CreateBookCommand;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateBookDto(
    @NotBlank(message = "Nome do livro não pode ser nulo")
    String bookName,

    String description,

    String link,

    Integer numPagesRead,

    @NotNull(message = "Numero de páginas do livro não pode ser nulo.")
    Integer numPagesFull
) {
    public CreateBookCommand toCommand() {
        return new CreateBookCommand(
            bookName,
            description,
            link,
            numPagesRead,
            numPagesFull
        );
    }
}
