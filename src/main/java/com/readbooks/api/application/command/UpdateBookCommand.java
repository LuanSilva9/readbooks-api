package com.readbooks.api.application.command;

public record UpdateBookCommand(
    String bookName,
    String description,
    String link,
    Integer numPagesFull
) {}