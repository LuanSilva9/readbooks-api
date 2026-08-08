package com.readbooks.api.infrastructure.controllers.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

public record ReadSummaryDto(
    UUID id,
    String summary_content,
    LocalDateTime created_at,
    LocalDateTime updated_at
) {}