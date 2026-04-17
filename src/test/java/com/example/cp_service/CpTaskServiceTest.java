package com.example.cp_service;

import com.example.cp_service.dto.CpTaskResponse;
import com.example.cp_service.dto.CreateTaskRequest;
import com.example.cp_service.entity.CpTask;
import com.example.cp_service.entity.enums.TaskStatus;
import com.example.cp_service.exception.NotFoundException;
import com.example.cp_service.repository.CpTaskRepository;
import com.example.cp_service.service.CpTaskService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CpTaskServiceTest {

    @Mock
    private CpTaskRepository repository;

    @InjectMocks
    private CpTaskService service;

    @Test
    void shouldCreateTask() {
        CreateTaskRequest request = new CreateTaskRequest("test.zip");

        when(repository.save(any())).thenAnswer(invocation -> {
            CpTask task = invocation.getArgument(0);
            task.setId(UUID.randomUUID());
            return task;
        });

        when(repository.findById(any())).thenAnswer(invocation -> {
            UUID id = invocation.getArgument(0);
            CpTask task = new CpTask();
            task.setId(id);
            return Optional.of(task);
        });

        UUID result = service.createTask(request);

        assertNotNull(result);
        verify(repository, times(3)).save(any());;
    }

    @Test
    void shouldReturnTask() {
        UUID id = UUID.randomUUID();

        CpTask task = new CpTask();
        task.setId(id);
        task.setFileName("test.zip");
        task.setStatus(TaskStatus.DONE);

        when(repository.findById(id)).thenReturn(Optional.of(task));

        CpTaskResponse response = service.getTask(id);

        assertEquals(id, response.id());
        assertEquals("test.zip", response.fileName());
    }

    @Test
    void shouldThrowExceptionWhenTaskNotFound() {
        UUID id = UUID.randomUUID();

        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> service.getTask(id));
    }
}
