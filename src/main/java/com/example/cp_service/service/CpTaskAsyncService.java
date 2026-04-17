package com.example.cp_service.service;

import com.example.cp_service.entity.CpTask;
import com.example.cp_service.entity.enums.TaskStatus;
import com.example.cp_service.repository.CpTaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CpTaskAsyncService {

    private final CpTaskRepository repository;

    @Async("taskExecutor")
    public void processAsync(UUID taskId) {
        CpTask task = repository.findById(taskId).orElseThrow();

        try {
            task.setStatus(TaskStatus.PROCESSING);
            repository.save(task);

            task.setExtractedData("{\"items\": []}");
            // doing smth
            Thread.sleep(5000);

            task.setStatus(TaskStatus.COMPLETED);
        } catch (Exception e) {
            task.setStatus(TaskStatus.FAILED);
            task.setError(e.getMessage());
        }

        repository.save(task);
    }
}
