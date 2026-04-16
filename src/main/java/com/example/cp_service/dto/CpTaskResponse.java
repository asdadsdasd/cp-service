package com.example.cp_service.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record CpTaskResponse(
        UUID id,
        String fileName,
        String status,
        String extractedData,
        String error,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}
