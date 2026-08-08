package com.readbooks.api.infrastructure.controllers.dto.request;

import com.readbooks.api.application.command.NewProgressBookCommand;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record NewProgressBookDto (
    @Min(value = 1, message = "Páginas lidas não podem ser menores que 1, pois significa que você não leu nada.")
    Integer pagesRead,

    @NotBlank(message = "O resumo diário é obrigatório")
    String dailySummaryContent
) {
    public NewProgressBookCommand toCommand() {
        return new NewProgressBookCommand(
            pagesRead,
            dailySummaryContent
        );
    }
}