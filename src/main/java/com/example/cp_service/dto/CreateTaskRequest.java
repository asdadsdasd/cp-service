package com.example.cp_service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Запрос на создание задачи")
public record CreateTaskRequest(

        @Schema(description = "Имя файла", example = "test.zip")
        @NotBlank(message = "fileName must not be blank")
        String fileName

) {}
