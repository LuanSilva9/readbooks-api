package com.readbooks.api.application.command;

public record CreateBookCommand(
    String bookName,
    String description,
    String link,
    Integer numPagesRead,
    Integer numPagesFull
) {}