package com.readbooks.api.infrastructure.mappers;

import java.util.List;

import org.springframework.stereotype.Component;
import com.readbooks.api.domain.model.SummaryDaily;
import com.readbooks.api.infrastructure.controllers.dto.response.ReadSummaryDto;

@Component
public class SummaryDailyMapper {
    public ReadSummaryDto entityToDto(SummaryDaily entity) {
        return new ReadSummaryDto(
            entity.getId(),
            entity.getSummaryContent(),
            entity.getCreatedAt(),
            entity.getUpdatedAt()
        );
    }

    public List<ReadSummaryDto> entityToDtoList(List<SummaryDaily> entities) {
        return entities.stream().map(this::entityToDto).toList();
    }
}
