package com.job_board_app.repository;

import com.job_board_app.model.JobItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.UUID;

public interface JobItemRepository extends JpaRepository<JobItem, UUID>, JpaSpecificationExecutor<JobItem> {
    List<JobItem> findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String title, String description);
}