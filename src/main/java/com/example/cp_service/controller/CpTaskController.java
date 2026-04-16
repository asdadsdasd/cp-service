package com.example.cp_service.controller;

import com.example.cp_service.dto.CpTaskResponse;
import com.example.cp_service.dto.CreateTaskRequest;
import com.example.cp_service.dto.CreateTaskResponse;
import com.example.cp_service.service.CpTaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/cp/tasks")
@RequiredArgsConstructor
public class CpTaskController {

    private final CpTaskService service;

    @PostMapping
    public CreateTaskResponse create(@RequestBody CreateTaskRequest request) {
        UUID id = service.createTask(request);
        return new CreateTaskResponse(id);
    }

    @GetMapping("/{id}")
    public CpTaskResponse get(@PathVariable UUID id) {
        return service.getTask(id);
    }
}
