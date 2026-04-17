package com.example.cp_service.dto;


import jakarta.validation.constraints.NotBlank;

public record CreateTaskRequest(
        @NotBlank(message = "fileName must not be blank")
        String fileName
) {}
