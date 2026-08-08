package com.readbooks.api.application.command;

public record NewProgressBookCommand(
    Integer pagesRead,
    String dailySummaryContent
) {}