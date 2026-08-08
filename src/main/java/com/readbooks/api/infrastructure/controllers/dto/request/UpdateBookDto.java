package com.readbooks.api.infrastructure.controllers.dto.request;

import com.readbooks.api.application.command.UpdateBookCommand;

public record UpdateBookDto(
    String bookName,
    String description,
    String link,
    Integer numPagesFull
) {
    public UpdateBookCommand toCommand() {
        return new UpdateBookCommand(
            bookName,
            description,
            link,
            numPagesFull
        );
    }
}