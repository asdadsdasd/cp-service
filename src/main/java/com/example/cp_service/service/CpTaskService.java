package com.example.cp_service.service;

import com.example.cp_service.dto.CpTaskResponse;
import com.example.cp_service.dto.CreateTaskRequest;
import com.example.cp_service.entity.CpTask;
import com.example.cp_service.entity.enums.TaskStatus;
import com.example.cp_service.exception.NotFoundException;
import com.example.cp_service.mapper.CpTaskMapper;
import com.example.cp_service.repository.CpTaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CpTaskService {

    private final CpTaskRepository repository;

    public UUID createTask(CreateTaskRequest request) {
        CpTask task = new CpTask();
        task.setFileName(request.fileName());
        task.setStatus(TaskStatus.NEW);

        repository.save(task);

        processAsync(task.getId());

        return task.getId();
    }

    @Async
    public void processAsync(UUID taskId) {
        CpTask task = repository.findById(taskId)
                .orElseThrow();

        try {
            task.setStatus(TaskStatus.PROCESSING);
            repository.save(task);

            // doing smth
            Thread.sleep(5000);

            task.setExtractedData("{\"items\": []}");
            task.setStatus(TaskStatus.DONE);

        } catch (Exception e) {
            task.setStatus(TaskStatus.ERROR);
            task.setError(e.getMessage());
        }

        task.setUpdatedAt(LocalDateTime.now());
        repository.save(task);
    }

    public CpTaskResponse getTask(UUID id) {
        CpTask task = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Task with id:" + id + " not found"));

        return CpTaskMapper.toResponse(task);
    }
}
