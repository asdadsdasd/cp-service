package com.example.cp_service.service;

import com.example.cp_service.dto.CpTaskResponse;
import com.example.cp_service.dto.CreateTaskRequest;
import com.example.cp_service.entity.CpTask;
import com.example.cp_service.entity.enums.TaskStatus;
import com.example.cp_service.exception.NotFoundException;
import com.example.cp_service.mapper.CpTaskMapper;
import com.example.cp_service.repository.CpTaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CpTaskService {

    private final CpTaskRepository repository;
    private final CpTaskAsyncService asyncService;

    public UUID createTask(CreateTaskRequest request) {
        CpTask task = new CpTask();
        task.setFileName(request.fileName());
        task.setStatus(TaskStatus.PENDING);

        repository.save(task);

        asyncService.processAsync(task.getId());

        return task.getId();
    }

    public CpTaskResponse getTask(UUID id) {
        CpTask task = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Task with id:" + id + " not found"));

        return CpTaskMapper.toResponse(task);
    }
}
