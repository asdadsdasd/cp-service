package com.example.cp_service.mapper;

import com.example.cp_service.dto.CpTaskResponse;
import com.example.cp_service.entity.CpTask;

public class CpTaskMapper {

    public static CpTaskResponse toResponse(CpTask task) {
        return new CpTaskResponse(
                task.getId(),
                task.getFileName(),
                task.getStatus().name(),
                task.getExtractedData(),
                task.getError(),
                task.getCreatedAt(),
                task.getUpdatedAt()
        );
    }
}
