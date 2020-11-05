package com.pepkor.kanban.repository;

import com.pepkor.kanban.model.Status;
import com.pepkor.kanban.entity.WorkItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkItemRepository extends JpaRepository<WorkItem, Long> {

    List<WorkItem> findByStatus(Status status);
}
