package com.readbooks.api.infrastructure.controllers.dto.response;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record ReadBookDto(
    UUID id,
    String book_name,
    String description,
    String link,
    LocalDate start_date,
    LocalDate end_date,
    Integer num_pages_read,
    Integer num_pages_full,
    List<ReadSummaryDto> summary_dailies 
) {}