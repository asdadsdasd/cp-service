package com.example.cp_service.controller;

import com.example.cp_service.dto.CpTaskResponse;
import com.example.cp_service.dto.CreateTaskRequest;
import com.example.cp_service.dto.CreateTaskResponse;
import com.example.cp_service.service.CpTaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Tag(name = "CP Tasks", description = "API для обработки коммерческих предложений")
@RestController
@RequestMapping("/cp/tasks")
@RequiredArgsConstructor
public class CpTaskController {

    private final CpTaskService service;

    @Operation(summary = "Создать задачу обработки КП")
    @PostMapping
    public CreateTaskResponse create(@Valid @RequestBody CreateTaskRequest request) {
        UUID id = service.createTask(request);
        return new CreateTaskResponse(id);
    }

    @Schema(description = "Запрос на создание задачи")
    @GetMapping("/{id}")
    public CpTaskResponse get(@PathVariable UUID id) {
        return service.getTask(id);
    }
}
