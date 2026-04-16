package com.example.cp_service.repository;

import com.example.cp_service.entity.CpTask;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CpTaskRepository extends JpaRepository<CpTask, UUID> {
}
