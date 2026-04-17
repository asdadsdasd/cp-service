package com.example.cp_service.dto;

import java.time.OffsetDateTime;
import java.util.UUID;

public record CpTaskResponse(
        UUID id,
        String fileName,
        String status,
        String extractedData,
        String error,
        OffsetDateTime createdAt,
        OffsetDateTime updatedAt
) {}
